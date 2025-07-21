package com.lukamurisic.lumiform_code_challenge.presentation.main

import com.lukamurisic.lumiform_code_challenge.domain.model.Page
import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseError
import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseViewState

data class MainViewState(
    val pages : List<Page> = emptyList(),
    override val isLoading: Boolean = false,
    override val error: BaseError? = null
) : BaseViewState(isLoading, error)