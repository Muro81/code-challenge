package com.lukamurisic.lumiform_code_challenge.domain.model

sealed class PageItem {

    data class Section(
        val title : String,
        val items : List<PageItem>
    ) : PageItem()

    data class Question(
        val title : String,
        val src : String?
    ) : PageItem()
}