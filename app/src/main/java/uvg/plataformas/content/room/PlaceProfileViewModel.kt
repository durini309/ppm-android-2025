package uvg.plataformas.content.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaceProfileViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val placeDao = database.placeDao()

    private val _state = MutableStateFlow(PlaceProfileScreenState(isLoading = true))
    val state: StateFlow<PlaceProfileScreenState> = _state.asStateFlow()

    private val placeId: Int = savedStateHandle.toRoute<RoomRoutes.PlaceProfile>().id

    init {
        loadPlace()
    }

    private fun loadPlace() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, isError = false) }
                val place = placeDao.getPlaceById(placeId)
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        data = place
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun updatePlace(place: PlaceEntity) {
        viewModelScope.launch {
            try {
                placeDao.updatePlace(place)
                _state.update { it.copy(data = place) }
            } catch (e: Exception) {
                _state.update { it.copy(isError = true) }
            }
        }
    }
}
