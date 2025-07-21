package com.lukamurisic.lumiform_code_challenge.core.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.NavType
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.Navigator
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.ObserveAsEvents
import timber.log.Timber

/** recreateApp() is hte function that is responsible for recreating the app once
the user chooses to change language setting for their app
it should be passed down to the screen(s) where the user has the lang change
option so it can be called from that screen and launched in MainActivity
 **/

@Composable
fun CodeChallengeNavigation(
    navController: NavHostController,
    navigator: Navigator,
    recreateApp: () -> Unit
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
        rootNavGraph(navController = navController)
    }
}


