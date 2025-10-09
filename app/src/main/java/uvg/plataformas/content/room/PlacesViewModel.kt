package uvg.plataformas.content.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlacesViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val placeDao = database.placeDao()

    // Flow version (para demostrar la diferencia, pero no se usa aún)
    val placesFlow: StateFlow<List<PlaceSummary>> = placeDao.getAllPlacesFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deletePlace(id: Int) {
        viewModelScope.launch {
            try {
                placeDao.deletePlace(id)
            } catch (e: Exception) {

            }
        }
    }

    fun deleteAllPlaces() {
        viewModelScope.launch {
            try {
                placeDao.deleteAllPlaces()
            } catch (e: Exception) {

            }
        }
    }

    fun insertDummyData() {
        viewModelScope.launch {
            try {
                val dummyPlaces = listOf(
                    PlaceEntity(name = "Antigua Guatemala", address = "5a Calle Poniente #15", lat = 14.5586f, long = -90.7339f, schedule = "Lunes a Domingo 8:00-18:00"),
                    PlaceEntity(name = "Cerro de la Cruz", address = "Antigua Guatemala", lat = 14.5617f, long = -90.7312f, schedule = "24 horas"),
                    PlaceEntity(name = "Parque Central", address = "Centro Antigua", lat = 14.5594f, long = -90.7336f, schedule = "24 horas"),
                    PlaceEntity(name = "Museo del Jade", address = "Zona 10, Guatemala", lat = 14.5955f, long = -90.5116f, schedule = "Martes a Domingo 10:00-17:00"),
                    PlaceEntity(name = "Tikal", address = "Petén, Guatemala", lat = 17.2221f, long = -89.6233f, schedule = "Lunes a Domingo 6:00-18:00"),
                    PlaceEntity(name = "Lago Atitlán", address = "Sololá, Guatemala", lat = 14.6906f, long = -91.2025f, schedule = "24 horas"),
                    PlaceEntity(name = "Chichicastenango", address = "El Quiché, Guatemala", lat = 14.9442f, long = -91.1119f, schedule = "Jueves y Domingo 8:00-17:00"),
                    PlaceEntity(name = "Semuc Champey", address = "Alta Verapaz, Guatemala", lat = 15.7894f, long = -89.9833f, schedule = "Lunes a Domingo 8:00-18:00"),
                    PlaceEntity(name = "Pacaya", address = "Escuintla, Guatemala", lat = 14.3811f, long = -90.6014f, schedule = "Lunes a Domingo 6:00-14:00"),
                    PlaceEntity(name = "Castillo San Felipe", address = "Río Dulce, Izabal", lat = 15.6333f, long = -88.9833f, schedule = "Lunes a Domingo 8:00-17:00")
                )
                dummyPlaces.forEach { place ->
                    placeDao.insertPlace(place)
                }
            } catch (e: Exception) {

            }
        }
    }
}
