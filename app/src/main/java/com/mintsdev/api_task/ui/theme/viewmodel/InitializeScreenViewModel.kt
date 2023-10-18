package com.mintsdev.api_task.ui.theme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintsdev.api_task.api.ApiClient
import com.mintsdev.api_task.api.Constants
import kotlinx.coroutines.launch


class InitializeScreenViewModel: ViewModel() {
    private val _apiAddress = MutableLiveData<String>()
    val apiAddress: LiveData<String> = _apiAddress



    fun fetchApiAddress() {
            viewModelScope.launch {
                try {
                    val response = ApiClient.apiService.getApiAddress(Constants.appName, Constants.v)
                    if (response.isSuccessful) {
                        _apiAddress.value = response.body()?.route
                    } else {
                        println("error")
                    }
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }

    }
}