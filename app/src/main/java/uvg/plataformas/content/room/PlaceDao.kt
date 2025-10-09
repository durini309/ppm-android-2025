package uvg.plataformas.content.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

data class PlaceSummary(
    val id: Int,
    val name: String,
    val address: String
)

@Dao
interface PlaceDao {
    @Query("SELECT id, name, address FROM places")
    suspend fun getAllPlaces(): List<PlaceSummary>

    @Query("SELECT id, name, address FROM places")
    fun getAllPlacesFlow(): Flow<List<PlaceSummary>>

    @Query("SELECT * FROM places WHERE id = :id")
    suspend fun getPlaceById(id: Int): PlaceEntity?

    @Query("DELETE FROM places WHERE id = :id")
    suspend fun deletePlace(id: Int)

    @Delete
    suspend fun deletePlace(place: PlaceEntity)

    @Query("DELETE FROM places")
    suspend fun deleteAllPlaces()

    @Insert
    suspend fun insertPlace(place: PlaceEntity)

    @Update
    suspend fun updatePlace(place: PlaceEntity)
}
