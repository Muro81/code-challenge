package com.lukamurisic.lumiform_code_challenge.presentation.question_image

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.Navigator
import com.lukamurisic.lumiform_code_challenge.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionImageViewModel @Inject constructor(
    private val navigator: Navigator,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<QuestionImageViewState, QuestionImageEvent>() {
    override val initialViewState = QuestionImageViewState()
    override fun dismissError() {
        state.update { it.copy(error = null) }
    }

    init {
        loadData()
    }

    fun onEvent(event : QuestionImageEvent){
        when(event){
            QuestionImageEvent.OnBackClick -> {
                navigateBack()
            }
        }
    }

    private fun navigateBack(){
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }
    private fun loadData() {
        state.update {
            it.copy(
                imagePath = savedStateHandle["imagePath"] ?: "",
                title = savedStateHandle["title"] ?: ""
            )
        }
    }
}