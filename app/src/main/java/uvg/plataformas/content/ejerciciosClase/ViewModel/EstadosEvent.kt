package uvg.plataformas.content.ejerciciosClase.ViewModel

sealed interface EstadosEvent {
    data object ReiniciarEstado: EstadosEvent
}