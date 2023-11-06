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
import retrofit2.Response

class CatalogViewModel(application: Application) : AndroidViewModel(application) {

    init {
        Log.d("CatalogViewModel", "ViewModel created")
    }

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    private val tokenFlow = StoreManager.getTokenKey(context)

    private val _brandsData = MutableLiveData<Map<String, BrandData>?>()
    val brandsData: MutableLiveData<Map<String, BrandData>?> = _brandsData


    fun loadCatalog() {
        Log.d("CatalogViewModel", "loadCatalog called")
        viewModelScope.launch {
            try {

                val response = fetchBrandsData()
                if (response.isSuccessful) {
                    val brands = response.body()?.brands
                    if (brands != null) {
                        _brandsData.value = brands
                        Log.d("response_success", "response_success")
                        for ((brandKey, brandData) in brands) {
                            Log.d("BrandData", "Key: $brandKey, BrandName: ${brandData.brandName}, BrandId: ${brandData.brandId}, BrandImg: ${brandData.brandImage}")
                        }
                    }
                } else {
                    Log.d("response_unsuccessfully", "response_unsuccessfully")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun fetchBrandsData(): Response<Brands> {
        return ApiClientMain.apiServiceMain.getBrands(TokenObj.tokenobj)
    }
}