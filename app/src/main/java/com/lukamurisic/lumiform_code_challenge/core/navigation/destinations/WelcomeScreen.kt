package com.lukamurisic.lumiform_code_challenge.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen
import com.lukamurisic.lumiform_code_challenge.presentation.welcome.WelcomeScreen
import com.lukamurisic.lumiform_code_challenge.presentation.welcome.WelcomeScreenViewModel

fun NavGraphBuilder.welcomeScreenComposable(
    navController: NavController
) {
        composable<Screen.WelcomeScreen> {
            val viewModel = hiltViewModel<WelcomeScreenViewModel>()
            WelcomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

}