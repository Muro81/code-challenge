package com.lukamurisic.lumiform_code_challenge

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
This is the Application class for the project.
 *- Annotate this class with `@HiltAndroidApp` to initialize Hilt's dependency injection system.
 * - This class serves as the entry point for dependency injection throughout the app.
 **/

@HiltAndroidApp
class CodeChallengeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites()
                    .detectNetwork().penaltyLog().build()
            )

            // Initialize strict mode
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().penaltyLog()
                    .penaltyDeath().build()
            )

            Timber.plant(Timber.DebugTree())
        }
    }
}