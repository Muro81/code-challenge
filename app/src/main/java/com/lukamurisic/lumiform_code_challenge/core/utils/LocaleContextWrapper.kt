package com.lukamurisic.lumiform_code_challenge.core.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.LocaleList
import java.util.Locale

class LocaleContextWrapper(base: Context?) : ContextWrapper(base) {
    companion object {
        fun wrap(ctx: Context, newLocale: Locale): LocaleContextWrapper {
            var context = ctx
            val res: Resources = context.resources
            val configuration: Configuration = res.configuration
            configuration.setLocale(newLocale)
            val localeList = LocaleList(newLocale)
            LocaleList.setDefault(localeList)
            configuration.setLocales(localeList)
            context = context.createConfigurationContext(configuration)

            return LocaleContextWrapper(context)
        }
    }
}