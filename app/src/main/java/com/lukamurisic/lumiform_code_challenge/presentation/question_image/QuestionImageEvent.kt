package com.lukamurisic.lumiform_code_challenge.presentation.question_image

import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseEvent


sealed class QuestionImageEvent : BaseEvent{
    data object OnBackClick : QuestionImageEvent()
}