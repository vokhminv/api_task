package com.mintsdev.api_task.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


class StoreManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

    companion object {
        val KEY_TOKEN = stringPreferencesKey("token_key")
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TOKEN] = token
        }
    }

    suspend fun getToken(): String? {
        val preferences = context.dataStore.data.first()
        return preferences[KEY_TOKEN]
    }
}
