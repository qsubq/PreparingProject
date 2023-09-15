package com.example.supabasetestproject.presentation.screen.main

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import java.util.Locale

class MyContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {
        fun wrap(ctx: Context, language: String): ContextWrapper {
            val config = ctx.resources.configuration
            val sysLocale: Locale?
            sysLocale = getSystemLocale(config)

            if (language != "" && sysLocale.language != language) {
                val locale = Locale(language)
                Locale.setDefault(locale)
                setSystemLocale(config, locale)
            }

            val context = ctx.createConfigurationContext(config)
            return MyContextWrapper(context)
        }

        private fun getSystemLocale(config: Configuration): Locale {
            return config.locales[0]
        }

        private fun setSystemLocale(config: Configuration, locale: Locale) {
            config.setLocale(locale)
        }
    }
}
