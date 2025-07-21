package com.lukamurisic.lumiform_code_challenge.presentation.base


open class BaseViewState(
    open val isLoading: Boolean,
    open val error: BaseError?
)