package com.faiz.patanistaticui.data

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPref(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")
        private val USER_IS_LOGIN = booleanPreferencesKey("isLogin")
        private val USER_EMAIL = stringPreferencesKey("userEmail")
        private val USER_ID = intPreferencesKey("userId")
    }

    val getUserIsLogin: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[USER_IS_LOGIN] ?: false
    }

    val getUserEmail: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_EMAIL] ?: ""
    }

    val getUserId: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[USER_ID] ?: 0
    }

    suspend fun saveToken(isLogin: Boolean, userEmail: String, userId:Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_IS_LOGIN] = isLogin
            preferences[USER_EMAIL] = userEmail
            preferences[USER_ID] = userId
        }
    }

    suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}