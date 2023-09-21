package com.example.supabasetestproject.presentation.screen.sign_in

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.supabasetestproject.domain.use_case.SignInUseCase
import kotlinx.coroutines.launch

class SignInViewModel(private val context: Application) : AndroidViewModel(context) {
    private val signInUseCase = SignInUseCase()

    val signInLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()
    val networkLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun signIn(email: String, password: String) {
        if (isOnline()) {
            viewModelScope.launch {
                signInUseCase.execute(email, password)
                signInLiveData.value = true
            }
        } else {
            networkLiveData.value = false
        }
    }

    private fun isOnline(): Boolean {
        val networkManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            networkManager.getNetworkCapabilities(networkManager.activeNetwork)

        return networkCapabilities != null
    }
}
