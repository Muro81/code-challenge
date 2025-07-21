package com.lukamurisic.lumiform_code_challenge.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen
import com.lukamurisic.lumiform_code_challenge.presentation.main.MainScreen
import com.lukamurisic.lumiform_code_challenge.presentation.main.MainViewModel

fun NavGraphBuilder.mainScreenComposable(
    showSnackBar: (String) -> Unit
) {
    composable<Screen.MainScreen> {
        val viewModel = hiltViewModel<MainViewModel>()
        MainScreen(
            viewModel = viewModel,
            showSnackBar = showSnackBar
        )
    }

}