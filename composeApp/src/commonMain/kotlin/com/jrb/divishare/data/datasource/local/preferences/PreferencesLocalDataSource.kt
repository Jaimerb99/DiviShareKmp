package com.jrb.divishare.data.datasource.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class PreferencesLocalDataSource(
    private val dataStore: DataStore<Preferences>
) {
    private val hasSeenOnboardingKey = booleanPreferencesKey("HAS_SEEN_ONBOARDING")
    private val accessTokenKey = stringPreferencesKey("ACCESS_TOKEN")
    private val userIdKey = stringPreferencesKey("USER_ID")

    suspend fun getHasSeenOnboarding(): Boolean {
        return dataStore.data.map { it[hasSeenOnboardingKey] ?: false }.firstOrNull() ?: false
    }

    suspend fun setHasSeenOnboarding(hasSeen: Boolean) {
        dataStore.edit { it[hasSeenOnboardingKey] = hasSeen }
    }

    // --- Métodos para el USER ID ---
    suspend fun getUserId(): String? {
        return dataStore.data.map { it[userIdKey] }.firstOrNull()
    }

    suspend fun setUserId(userId: String?) {
        dataStore.edit { preferences ->
            if (userId != null) {
                preferences[userIdKey] = userId
            } else {
                preferences.remove(userIdKey)
            }
        }
    }

    suspend fun getAccessToken(): String? {
        return dataStore.data.map { it[accessTokenKey] }.firstOrNull()
    }

    suspend fun setAccessToken(token: String?) {
        dataStore.edit { preferences ->
            if (token != null) {
                preferences[accessTokenKey] = token
            } else {
                preferences.remove(accessTokenKey)
            }
        }
    }

    suspend fun isUserLoggedIn(): Boolean {
        return getAccessToken() != null && getUserId() != null
    }

    suspend fun clearPreferences() {
        dataStore.edit { it.clear() }
    }
}