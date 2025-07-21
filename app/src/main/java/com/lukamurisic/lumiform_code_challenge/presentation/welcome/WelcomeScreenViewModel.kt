package com.lukamurisic.lumiform_code_challenge.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    fun onEvent(event: WelcomeEvent) {
        when (event) {
            is WelcomeEvent.OnGoToEnterDataClick -> {
            }
        }
    }
}


sealed class WelcomeEvent {
    data object OnGoToEnterDataClick : WelcomeEvent()
}