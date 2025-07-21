package com.lukamurisic.lumiform_code_challenge.presentation.main

import com.lukamurisic.lumiform_code_challenge.domain.model.PageItem
import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseEvent

sealed class MainEvent : BaseEvent{
    data class OnImageClick(val question : PageItem.Question) : MainEvent()
}