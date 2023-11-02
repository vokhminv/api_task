package com.mintsdev.api_task.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



object StoreManager {
    private const val DATASTORE_NAME = "my_datastore"
    private val TOKEN_KEY = stringPreferencesKey("token_key")

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    suspend fun saveTokenKey(context: Context, tokenkey: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = tokenkey
        }
    }

    fun getTokenKey(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }
}