package com.mintsdev.api_task.ui.theme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintsdev.api_task.api.ApiClient
import com.mintsdev.api_task.api.AuthInfo
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    fun authenticate() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getToken(
                    AuthInfo.login,
                    AuthInfo.password,
                    AuthInfo.devman,
                    AuthInfo.devmod,
                    AuthInfo.devavs,
                    AuthInfo.devaid
                )
                if (response.isSuccessful) {
                    val tokenResponse = response.body()
                    _token.value = tokenResponse?.token ?: "where is this fucking token"
                } else {

                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}