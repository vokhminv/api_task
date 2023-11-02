package com.mintsdev.api_task.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_datastore")

object StoreManager {
    private val TOKEN_KEY = stringPreferencesKey("token_key")



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