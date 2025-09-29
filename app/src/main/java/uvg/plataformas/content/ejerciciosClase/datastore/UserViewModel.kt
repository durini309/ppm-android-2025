package uvg.plataformas.content.ejerciciosClase.datastore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application.dataStore)

    val isLoggedIn = userPreferences.isLoggedIn
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    private val _loggedInState = MutableStateFlow<Boolean?>(null)
    val loggedInState = _loggedInState.asStateFlow()

    fun getLoggedInFlag() {
        viewModelScope.launch {
            val result = userPreferences.getLoggedIn()
            _loggedInState.update { result }
        }
    }

    fun setLoggedIn(loggedIn: Boolean) {
        viewModelScope.launch {
            userPreferences.setLoggedIn(loggedIn)
        }
    }
}