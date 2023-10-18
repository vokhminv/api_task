package com.mintsdev.api_task.ui.theme.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

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

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)


            return network != null && networkCapabilities != null &&
                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo

            return networkInfo != null && networkInfo.isConnected
        }
    }



    fun fetchApiAddress(context: Context) {
        if (isInternetAvailable(context)) {
            viewModelScope.launch {
                try {
                    val response =
                        ApiClient.apiService.getApiAddress(Constants.appName, Constants.v)
                    if (response.isSuccessful) {
                        _apiAddress.value = "Recieved"
                        _connectionStatus.value = "Connected"
                    } else {
                        _apiAddress.value = "Unavailable"
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            _connectionStatus.value = "Check your connection"
        }
    }
}