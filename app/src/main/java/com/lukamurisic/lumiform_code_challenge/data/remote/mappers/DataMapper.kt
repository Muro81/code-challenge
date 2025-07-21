package com.lukamurisic.lumiform_code_challenge.data.remote.mappers

import com.lukamurisic.lumiform_code_challenge.core.utils.toNonNull
import com.lukamurisic.lumiform_code_challenge.data.remote.response.DataResponseDto
import com.lukamurisic.lumiform_code_challenge.domain.model.Page
import com.lukamurisic.lumiform_code_challenge.domain.model.PageItem

fun DataResponseDto.toPagesList(): List<Page> {
    val resultList = mutableListOf<Page>()

    if (this.type != "page") return resultList

    val mainPage = Page(
        title = this.title.toNonNull(),
        items = mapItemsToPageItems(this.items.orEmpty().filter { it.type != "page" })
    )
    resultList.add(mainPage)

    this.items.orEmpty().filter { it.type == "page" }.forEach { nestedPage ->
        resultList.add(
            Page(
                title = nestedPage.title.toNonNull(),
                items = mapItemsToPageItems(nestedPage.items.orEmpty())
            )
        )
    }

    return resultList
}


fun mapItemsToPageItems(items: List<DataResponseDto>): List<PageItem> {
    return items.mapNotNull { item ->
        when (item.type) {
            "section" -> PageItem.Section(
                title = item.title.orEmpty(),
                items = mapItemsToPageItems(item.items.orEmpty())
            )
            "text", "image" -> PageItem.Question(
                title = item.title.orEmpty(),
                src = item.src
            )
            else -> null
        }
    }
}