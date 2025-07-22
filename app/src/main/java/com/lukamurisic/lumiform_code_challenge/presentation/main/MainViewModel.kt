package com.lukamurisic.lumiform_code_challenge.presentation.main

import androidx.lifecycle.viewModelScope
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen.QuestionImageScreen
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.Navigator
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.onError
import com.lukamurisic.lumiform_code_challenge.core.utils.networking.onSuccess
import com.lukamurisic.lumiform_code_challenge.domain.model.PageItem
import com.lukamurisic.lumiform_code_challenge.domain.repository.CodeChallengeRepository
import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CodeChallengeRepository,
    private val navigator: Navigator
) : BaseViewModel<MainViewState, MainEvent>() {
    override val initialViewState = MainViewState()
    override fun dismissError() {
        state.update { it.copy(error = null) }
    }

    init {
        getPages()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnImageClick -> {
                onImageClick(event.question)
            }
        }
    }

    private fun onImageClick(question: PageItem.Question) {
        viewModelScope.launch {
            navigator.navigateTo(
                destination = QuestionImageScreen(
                    imagePath = question.src ?: "",
                    title = question.title
                )
            )
        }
    }


    private fun getPages() {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = true
                )
            }
            repository.getDataRemote().onSuccess { res ->
                state.update {
                    it.copy(
                        pages = res
                    )
                }
                repository.insertPages(state.value.pages)
                state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }.onError {
                _snackBarChannel.send(it.name)
                getPagesLocal()
            }

        }
    }

    private fun getPagesLocal() {
        viewModelScope.launch {
            val pages = repository.getDataLocal()
            state.update {
                it.copy(
                    pages = pages,
                    isLoading = false
                )
            }
        }
    }
}