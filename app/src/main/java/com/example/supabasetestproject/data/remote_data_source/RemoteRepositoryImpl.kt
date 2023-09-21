package com.example.supabasetestproject.data.remote_data_source

import android.net.Uri
import com.example.supabasetestproject.data.model.UserModel
import com.example.supabasetestproject.data.model.UserModelResponse
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Returning
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.storage
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.io.File
import java.net.URI

class RemoteRepositoryImpl {
    private val client = createSupabaseClient(
        supabaseUrl = "https://kepofsjhmtkutvieibrs.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtlcG9mc2pobXRrdXR2aWVpYnJzIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODg0NjQwMzQsImV4cCI6MjAwNDA0MDAzNH0.3Q1--FQgCgDb3naCRbNO1ZbkueAAP571VGJR36M5AkQ",
    ) {
        install(GoTrue)
        install(Postgrest)
        install(Realtime)
    }

    suspend fun signUp(email: String, password: String, name: String, age: Int, phone: String) {
        client.gotrue.signUpWith(Email) {
            this.email = email
            this.password = password
            this.data = buildJsonObject {
                put("name", name)
                put("age", age)
            }
        }
        val user = UserModel(
            avatar = "",
            balance = "",
            name = "NameOfJohn",
            rider = false,
            email = email,
            phone = phone,
            transactions = null,
            unread_count = 0,
        )
        client.postgrest["users"].insert(
            user,
            returning = Returning.MINIMAL,
        )
    }

    suspend fun signIn(email: String, password: String) {
        client.gotrue.loginWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun otpSignIn(email: String) {
        client.gotrue.sendOtpTo(Email) {
            this.email = email
        }
    }

    suspend fun changePassword(password: String) {
        client.gotrue.modifyUser {
            this.password = password
        }
    }

    suspend fun verifyOTP(email: String, token: String) {
        client.gotrue.verifyEmailOtp(
            type = OtpType.Email.MAGIC_LINK,
            email = email,
            token = token,
        )
    }

    suspend fun getUserInfoForEmail(email: String): List<UserModelResponse> {
        return client.postgrest["users"].select {
            UserModelResponse::email eq email
        }.decodeAs()
    }

    suspend fun uploadImage(file: Uri) {
        client.storage.createBucket(id = "avatars") {
            public = true
            fileSizeLimit = 10.megabytes
        }
        val bucket = client.storage["avatars"]
        val filledFile = File(URI(file.path))
        file.path?.let { bucket.upload(it, filledFile.readBytes(), upsert = false) }
    }
}
