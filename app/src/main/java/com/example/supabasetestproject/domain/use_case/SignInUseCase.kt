package com.example.supabasetestproject.domain.use_case

import com.example.supabasetestproject.data.remote_data_source.RemoteRepositoryImpl

class SignInUseCase() {
    private val repository: RemoteRepositoryImpl = RemoteRepositoryImpl()

    suspend fun execute(email: String, password: String) {
        repository.signIn(email, password)
    }
}
