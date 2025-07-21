package com.lukamurisic.lumiform_code_challenge

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.lukamurisic.lumiform_code_challenge.core.navigation.graphs.CodeChallengeNavigation
import com.lukamurisic.lumiform_code_challenge.core.navigation.utils.Navigator
import com.lukamurisic.lumiform_code_challenge.core.utils.CustomModifiers
import com.lukamurisic.lumiform_code_challenge.core.utils.rememberAppState
import com.lukamurisic.lumiform_code_challenge.ui.theme.LumiformCodeChallenge
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var navigator: Navigator

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {

            LumiformCodeChallenge {
                val appState = rememberAppState()
                val navController = rememberNavController()

                Scaffold(snackbarHost = {
                    CustomModifiers.snackBarHost(appState.scaffoldState)
                }) { innerPadding ->
                    CodeChallengeNavigation(
                        navController = navController,
                        navigator = navigator,
                        showSnackBar = { message ->
                            appState.showSnackBar(message)
                        }
                    )
                }
            }
        }
    }

    /**
     * Checks if a permission is already granted.
     */
    fun isPermissionGranted(context: android.content.Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PermissionChecker.PERMISSION_GRANTED
    }
}