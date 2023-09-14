package com.example.supabasetestproject.data.local_data_source

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.supabasetestproject.domain.repository.LocalRepository

open class LocalRepositoryImpl(context: Context) : LocalRepository {
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

    override fun setIsAlreadySeenOnBoarding() {
        sharedPreferences.edit().putBoolean("isAlreadySeenOnBoarding", true).apply()
    }

    override fun isAlreadySeenOnBoarding(): Boolean {
        return sharedPreferences.getBoolean("isAlreadySeenOnBoarding", false)
    }
}
