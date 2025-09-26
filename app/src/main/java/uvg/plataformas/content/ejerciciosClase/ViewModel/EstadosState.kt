package uvg.plataformas.content.ejerciciosClase.ViewModel

data class EstadosState(
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val winnerNumber: Int = 0
)
