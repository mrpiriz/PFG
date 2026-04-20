package com.gestion.gestionAlumnos.core.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "session_prefs")

class SessionManager(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val ROL_KEY = stringPreferencesKey("rol")
        private val EMAIL_KEY = stringPreferencesKey("email")
    }

    suspend fun saveSession(token: String, rol: String, email: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
            prefs[ROL_KEY] = rol
            prefs[EMAIL_KEY] = email
        }
    }

    suspend fun getToken(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[TOKEN_KEY]
    }

    suspend fun getRol(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[ROL_KEY]
    }

    suspend fun getEmail(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[EMAIL_KEY]
    }

    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}