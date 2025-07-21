package com.lukamurisic.lumiform_code_challenge.presentation.main

import androidx.lifecycle.viewModelScope
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.onError
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.onSuccess
import com.lukamurisic.lumiform_code_challenge.domain.repository.CodeChallengeRepository
import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : CodeChallengeRepository
) : BaseViewModel<MainViewState, MainEvent>(){
    override val initialViewState = MainViewState()
    override fun dismissError() {
        state.update { it.copy(error = null) }
    }

    init {
        getPages()
    }

    private fun getPages() {
        viewModelScope.launch {
            repository.getData().onSuccess { res ->
                state.update {
                    it.copy(
                       pages = res
                    )
                }
                Timber.e("PAGES ARE ${state.value.pages}")
            }.onError {
                Timber.e("ERROR IS ${it.name}")
                //TODO handle errors
            }
        }
    }
}