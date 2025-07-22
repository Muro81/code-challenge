package com.lukamurisic.lumiform_code_challenge.data.repository

import com.lukamurisic.lumiform_code_challenge.core.utils.networking.NetworkError
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.Resource
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.map
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.retrieveAPIResponse
import com.lukamurisic.lumiform_code_challenge.data.local.dao.ContentDao
import com.lukamurisic.lumiform_code_challenge.data.local.entity.PageEntity
import com.lukamurisic.lumiform_code_challenge.data.local.entity.QuestionEntity
import com.lukamurisic.lumiform_code_challenge.data.local.entity.SectionEntity
import com.lukamurisic.lumiform_code_challenge.data.local.mapper.toPage
import com.lukamurisic.lumiform_code_challenge.data.remote.mappers.toPagesList
import com.lukamurisic.lumiform_code_challenge.data.remote.response.DataResponseDto
import com.lukamurisic.lumiform_code_challenge.data.remote.services.ApiService
import com.lukamurisic.lumiform_code_challenge.domain.model.Page
import com.lukamurisic.lumiform_code_challenge.domain.model.PageItem
import com.lukamurisic.lumiform_code_challenge.domain.repository.CodeChallengeRepository
import timber.log.Timber
import javax.inject.Inject

class CodeChallengeRepositoryImpl@Inject constructor(private val apiService: ApiService,private val contentDao: ContentDao): CodeChallengeRepository{
    override suspend fun getDataRemote(): Resource<List<Page>, NetworkError> {
        return retrieveAPIResponse<DataResponseDto> {
            apiService.getData()
        }.map {
            it.toPagesList()
        }
    }

    override suspend fun getDataLocal(): List<Page> {

        val pagesWithContents = contentDao.getAllPagesWithContents()
        Timber.e("PAGES ARE $pagesWithContents")
        return pagesWithContents.map { it.toPage(contentDao) }
    }

    override suspend fun insertPages(pages: List<Page>): Boolean {
        return try {
            insertPagesOrdered(pages)
            true
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun insertPagesOrdered(pages: List<Page>) {
        pages.forEachIndexed { index, page ->
            val pageId = contentDao.insertPage(PageEntity(title = page.title, order = index)).toInt()
            insertPageItems(page.items, pageId, parentSectionId = null)
        }
    }

    private suspend fun insertPageItems(
        items: List<PageItem>,
        pageId: Int,
        parentSectionId: Int?
    ) {
        items.forEachIndexed { index, item ->
            when (item) {
                is PageItem.Section -> {
                    val sectionId = contentDao.insertSection(
                        SectionEntity(
                            title = item.title,
                            parentPageId = pageId ,
                            parentSectionId = parentSectionId,
                            order = index
                        )
                    ).toInt()
                    insertPageItems(item.items, pageId, sectionId)
                }

                is PageItem.Question -> {
                    contentDao.insertQuestion(
                        QuestionEntity(
                            title = item.title,
                            src = item.src,
                            parentPageId = pageId,
                            parentSectionId = parentSectionId,
                            order = index
                        )
                    )
                }
            }
        }
    }
}