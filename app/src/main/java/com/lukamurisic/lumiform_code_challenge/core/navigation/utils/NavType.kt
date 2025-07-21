package com.lukamurisic.lumiform_code_challenge.core.navigation.utils

import androidx.navigation.NavOptionsBuilder
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen

sealed class NavType {
    data object NavigateUp : NavType()

    data class NavigateToRoute(
        val destination: Screen,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ) : NavType()
}


