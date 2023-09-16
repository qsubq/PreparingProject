package com.example.supabasetestproject.presentation.screen.main

import android.content.Context
import android.content.ContextWrapper
import java.util.Locale

class MyContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {
        fun wrap(ctx: Context, language: String): ContextWrapper {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val config = ctx.resources.configuration
            config.setLocale(locale)

            val context = ctx.createConfigurationContext(config)

            return MyContextWrapper(context)
        }
    }
}
