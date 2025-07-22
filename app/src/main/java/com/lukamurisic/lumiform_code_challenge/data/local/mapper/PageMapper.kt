package com.lukamurisic.lumiform_code_challenge.data.local.mapper

import com.lukamurisic.lumiform_code_challenge.data.local.dao.ContentDao
import com.lukamurisic.lumiform_code_challenge.data.local.relation.PageWithContents
import com.lukamurisic.lumiform_code_challenge.domain.model.Page
import com.lukamurisic.lumiform_code_challenge.domain.model.PageItem

suspend fun PageWithContents.toPage(contentDao: ContentDao): Page {
    val items = buildPageItemsRecursive(
        pageId = page.id,
        parentSectionId = null,
        contentDao = contentDao
    )
    return Page(title = page.title, items = items)
}

suspend fun buildPageItemsRecursive(
    pageId: Int,
    parentSectionId: Int?,
    contentDao: ContentDao
): List<PageItem> {
    val combined = mutableListOf<Pair<Int, PageItem>>()

    val sections = contentDao.getSectionsByPageAndParent(pageId, parentSectionId)
    val questions = contentDao.getQuestionsByPageAndParent(pageId, parentSectionId)

    for (section in sections) {
        val children = buildPageItemsRecursive(pageId, section.id, contentDao)
        combined += section.order to PageItem.Section(title = section.title, items = children)
    }

    for (question in questions) {
        combined += question.order to PageItem.Question(title = question.title, src = question.src)
    }

    return combined.sortedBy { it.first }.map { it.second }
}