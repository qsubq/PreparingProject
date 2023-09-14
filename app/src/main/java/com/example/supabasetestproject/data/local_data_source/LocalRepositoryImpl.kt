package com.example.supabasetestproject.data.local_data_source

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class LocalRepositoryImpl(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )

    fun setIsAlreadySeenOnBoarding() {
        sharedPreferences.edit().putBoolean("isAlreadySeenOnBoarding", true).apply()
    }

    fun isAlreadySeenOnBoarding(): Boolean {
        return sharedPreferences.getBoolean("isAlreadySeenOnBoarding", false)
    }
}
