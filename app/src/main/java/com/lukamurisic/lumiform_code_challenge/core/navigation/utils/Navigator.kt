package com.lukamurisic.lumiform_code_challenge.core.navigation.utils

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen

interface Navigator {
    val navigationFlow: Flow<NavType>
    suspend fun navigateTo(
        destination: Screen,
        navOptions: NavOptionsBuilder.() -> Unit = {}
    )

    suspend fun navigateUp()
}


