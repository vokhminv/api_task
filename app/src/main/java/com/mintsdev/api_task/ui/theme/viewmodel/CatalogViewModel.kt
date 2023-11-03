package com.mintsdev.api_task.ui.theme.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mintsdev.api_task.api.ApiClientMain
import com.mintsdev.api_task.api.BrandData
import com.mintsdev.api_task.api.Brands
import com.mintsdev.api_task.api.TokenObj
import com.mintsdev.api_task.datastore.StoreManager
import kotlinx.coroutines.launch

class CatalogViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    private val tokenFlow = StoreManager.getTokenKey(context)

    private val _brandData = MutableLiveData<BrandData>()
    val brandData: LiveData<BrandData> = _brandData

    @SuppressLint("NullSafeMutableLiveData")
    fun loadCatalog() {
        Log.d("LoadCatalog", "LoadCatalog")
        viewModelScope.launch {
            try {
                val token = tokenFlow
                val response = ApiClientMain.apiServiceMain.getBrands(TokenObj.tokenobj)
                if (response.isSuccessful) {
                    val brandList = response.body()
                    if (brandList != null && brandList.brands.isNotEmpty()) {
                        _brandData.value = brandList.brands.values.first()
                        Log.d("response_success", "response_success")
                    }
                } else {
                    // API error here
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _brandData.value = null
            }
        }
    }
}