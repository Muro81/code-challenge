package com.lukamurisic.lumiform_code_challenge.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow


abstract class BaseViewModel<S : BaseViewState, E : BaseEvent> : ViewModel() {

    protected abstract val initialViewState: S
    protected val state by lazy { MutableStateFlow(initialViewState) }
    val viewStateFlow: StateFlow<S> by lazy { state }

    protected val _snackBarChannel = Channel<String>()
    val snackBarChannel = _snackBarChannel.receiveAsFlow()

    protected val event = MutableSharedFlow<E>()
    val eventFlow: SharedFlow<E> = event

    abstract fun dismissError()
}