package uvg.plataformas.content.room

data class PlaceProfileScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: PlaceEntity? = null
)
