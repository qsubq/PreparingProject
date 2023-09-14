package com.example.supabasetestproject.data.remote_data_source

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

class RemoteRepositoryImpl {
    val client = createSupabaseClient(
        supabaseUrl = "https://egtovzmzcxnecopqvrtf.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVndG92em16Y3huZWNvcHF2cnRmIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTQ1MTcxOTksImV4cCI6MjAxMDA5MzE5OX0.LFBbY6qWMZadKH9F5_lRKcoHaVMl7E7hi8Kep3MctPE",
    ) {
        install(GoTrue)
        install(Postgrest)
        install(Realtime)
    }

    suspend fun signUp(email: String, password: String) {
        client.gotrue.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signIn(email: String, password: String){
        client.gotrue.loginWith(Email) {
            this.email = email
            this.password = password
        }
    }
}
