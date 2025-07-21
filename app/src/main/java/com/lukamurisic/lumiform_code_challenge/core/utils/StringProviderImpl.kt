package com.lukamurisic.lumiform_code_challenge.core.utils

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import java.util.Locale
import javax.inject.Inject

class StringProviderImpl @Inject constructor(
    private val application: Application
) : StringProvider {
    private fun getLocalizedContext(): Context {
        val currentLocale = Locale.getDefault()
        val config = Configuration(application.resources.configuration)
        config.setLocale(currentLocale)
        return application.createConfigurationContext(config)
    }

    override fun getString(resId: Int): String {
        return getLocalizedContext().getString(resId)
    }

    override fun getStringWithArgs(resId: Int, vararg args: Any): String {
        return getLocalizedContext().getString(resId, *args)
    }
}