package com.lukamurisic.lumiform_code_challenge.core.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.NavType
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.Navigator
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.ObserveAsEvents
import timber.log.Timber


@Composable
fun CodeChallengeNavigation(
    navController: NavHostController,
    navigator: Navigator,
    showSnackBar: (String) -> Unit,
) {
    ObserveAsEvents(flow = navigator.navigationFlow) { navType ->
        Timber.e("TYPE $navType")
        when (navType) {
            is NavType.NavigateToRoute -> {
                navController.navigate(navType.destination) {
                    launchSingleTop = true
                    restoreState = true
                    navType.navOptions(this)
                }
            }

            NavType.NavigateUp -> {
                if (navController.previousBackStackEntry != null) {
                    navController.navigateUp()
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.RootGraph
    ) {
        rootNavGraph(showSnackBar = showSnackBar)
    }
}


