package uvg.plataformas.content.ejerciciosClase.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences(
    private val dataStore: DataStore<Preferences>
) {
    private val loggedIn = booleanPreferencesKey("logged_in")

    val isLoggedIn = dataStore.data.map { preferences ->
        preferences[loggedIn] ?: false
    }

    suspend fun setLoggedIn(loggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[this.loggedIn] = loggedIn
        }
    }

    suspend fun getLoggedIn(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[loggedIn] ?: false
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}