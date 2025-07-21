package com.lukamurisic.lumiform_code_challenge.core.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen
import com.lukamurisic.lumiform_code_challenge.core.navigation.destinations.welcomeScreenComposable

fun NavGraphBuilder.rootNavGraph(
    navController: NavController,
) {
    navigation<Screen.RootGraph>(
        startDestination = Screen.WelcomeScreen
    ) {
        welcomeScreenComposable(navController = navController)
    }
}