package com.mintsdev.api_task.ui.theme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintsdev.api_task.api.ApiClient
import com.mintsdev.api_task.api.Constants
import kotlinx.coroutines.launch


class InitializeScreenViewModel: ViewModel() {
    private var _apiAddress = MutableLiveData<String>()
    var apiAddress: LiveData<String> = _apiAddress

    private val _connectionStatus = MutableLiveData<String>()
    val connectionStatus: LiveData<String> = _connectionStatus


    fun fetchApiAddress() {
            viewModelScope.launch {
                try {
                    val response = ApiClient.apiService.getApiAddress(Constants.appName, Constants.v)
                    if (response.isSuccessful) {
                        _apiAddress.value = "Recieved"
                        _connectionStatus.value = "Connected"
                    } else {
                        _apiAddress.value = "Unavailable"
                        _connectionStatus.value = "Not connected"
                    }
                } catch (e: Exception){
                    e.printStackTrace()
                    _connectionStatus.value = "Not connected"
                }
            }

    }
}