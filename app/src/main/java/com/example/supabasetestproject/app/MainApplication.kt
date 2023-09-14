package com.example.supabasetestproject.app

import android.app.Application
import android.view.ContextThemeWrapper
import java.util.Locale

class MainApplication : Application() {
    fun changeLocale(language: String, wrapper: ContextThemeWrapper) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
//        resources.updateConfiguration(configuration, wrapper.resources.displayMetrics)
        wrapper.applyOverrideConfiguration(configuration)
    }
}
