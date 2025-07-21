package com.lukamurisic.lumiform_code_challenge.presentation.question_image

import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseError
import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseViewState

data class QuestionImageViewState(
    val imagePath : String = String(),
    val title : String = String(),
    override val isLoading: Boolean = false,
    override val error: BaseError? = null
) : BaseViewState(isLoading, error)