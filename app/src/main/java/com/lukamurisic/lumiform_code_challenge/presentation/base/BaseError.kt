package com.lukamurisic.lumiform_code_challenge.presentation.base

import androidx.annotation.StringRes

sealed class BaseError(
    @StringRes open val messageResId: Int
) : Throwable() {

    data class AlertError(
        @StringRes val title: Int? = null,
        override val messageResId: Int
    ) : BaseError(messageResId)

    data class InlineError(
        override val messageResId: Int
    ) : BaseError(messageResId)

    data class IgnoreError(override val messageResId: Int) : BaseError(messageResId)
}