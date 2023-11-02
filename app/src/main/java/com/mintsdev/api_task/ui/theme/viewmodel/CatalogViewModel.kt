package com.mintsdev.api_task.ui.theme.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.mintsdev.api_task.datastore.StoreManager
import kotlinx.coroutines.flow.Flow

class CatalogViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    var tokenFlow = StoreManager.getTokenKey(context)


}