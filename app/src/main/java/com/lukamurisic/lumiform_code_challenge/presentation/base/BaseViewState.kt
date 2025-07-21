package com.lukamurisic.lumiform_code_challenge.presentation.base

import androidx.annotation.StringRes

open class BaseViewState(
    open val isLoading: Boolean,
    open val error: BaseError?,
    @StringRes open val snackbarMessageResId: Int?
)