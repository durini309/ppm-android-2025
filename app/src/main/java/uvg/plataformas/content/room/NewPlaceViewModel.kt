package uvg.plataformas.content.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewPlaceViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val placeDao = database.placeDao()

    private val _state = MutableStateFlow(NewPlaceScreenState())
    val state: StateFlow<NewPlaceScreenState> = _state.asStateFlow()

    fun insertPlace(place: PlaceEntity) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, isError = false) }
                placeDao.insertPlace(place)
                _state.update { it.copy(isLoading = false, isError = false) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }
}
