package com.lukamurisic.lumiform_code_challenge.core.navigation.utils

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import com.lukamurisic.lumiform_code_challenge.core.navigation.Screen
import javax.inject.Inject
import javax.inject.Singleton

/**
Implementation of the [Navigator] interface that handles navigation events in the app.
This class uses Kotlin Channels to emit navigation events that can be collected and processed
by the UI layer.
This is done so that we don't break MVI patter by passing navController to our
composables and navigate directly from them.

@property _navigationChannel A Channel that emits [NavType] events for navigation.
@property navigationFlow A Flow that exposes navigation events to collectors.
 **/

@Singleton
class NavigatorImpl @Inject constructor() : Navigator {
    private val _navigationChannel = Channel<NavType>()
    override val navigationFlow = _navigationChannel.receiveAsFlow()

    override suspend fun navigateTo(
        destination: Screen,
        navOptions: NavOptionsBuilder.() -> Unit
    ) {
        _navigationChannel.send(
            NavType.NavigateToRoute(
                destination = destination,
                navOptions = navOptions
            )
        )
    }

    override suspend fun navigateUp() {
        _navigationChannel.send(NavType.NavigateUp)
    }
}







