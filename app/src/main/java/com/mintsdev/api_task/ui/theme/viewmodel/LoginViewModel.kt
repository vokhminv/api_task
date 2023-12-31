package com.mintsdev.api_task.ui.theme.viewmodel


import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mintsdev.api_task.api.ApiClientMain
import com.mintsdev.api_task.api.AuthInfo
import com.mintsdev.api_task.api.BrandData

import com.mintsdev.api_task.datastore.StoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    private val storeManager = StoreManager

    private val _token = MutableLiveData<String?>()
    val token: MutableLiveData<String?> = _token

    fun authenticate() {
        viewModelScope.launch {
            try {
                val response = ApiClientMain.apiServiceMain.getToken(
                    AuthInfo.login,
                    AuthInfo.password,
                    AuthInfo.devman,
                    AuthInfo.devmod,
                    AuthInfo.devavs,
                    AuthInfo.devaid
                )
                if (response.isSuccessful) {
                    val tokenResponse = response.body()
                    val token = tokenResponse?.token
                    _token.value = token
                    Log.d("saveToken", "SaveToken")
                    if(!token.isNullOrEmpty()) {
                        storeManager.saveTokenKey(context, token)
                    }


                } else {
                    _token.value = "Unavailable"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }





}