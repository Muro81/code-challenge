package com.lukamurisic.lumiform_code_challenge.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen
import com.lukamurisic.lumiform_code_challenge.core.navigation.destinations.mainScreenComposable

fun NavGraphBuilder.rootNavGraph(
    showSnackBar: (String) -> Unit
) {
    navigation<Screen.RootGraph>(
        startDestination = Screen.MainScreen
    ) {
        mainScreenComposable(showSnackBar = showSnackBar)
    }
}