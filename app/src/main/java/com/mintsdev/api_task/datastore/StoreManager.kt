package com.mintsdev.api_task.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map



object StoreManager {

    private lateinit var dataStore: DataStore<Preferences>

    fun initialize(context: Context) {
        dataStore = context.createDataStore(name = "data_store")
    }

    suspend fun saveToken(context: Context, token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_TOKEN] = token
        }
    }

    fun getTokenFlow(context: Context): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[KEY_TOKEN] ?: ""
        }
    }

    private val KEY_TOKEN = stringPreferencesKey("token_key")
}