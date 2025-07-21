package com.lukamurisic.lumiform_code_challenge.core.navigation

import kotlinx.serialization.Serializable


/**
This interface represents all navigation destinations in the app.
The sealed interface ensures type-safe navigation.
Discuss renaming this to Destination
 **/

sealed interface Screen {

    @Serializable
    data object RootGraph : Screen

    @Serializable
    data object WelcomeScreen : Screen
}

