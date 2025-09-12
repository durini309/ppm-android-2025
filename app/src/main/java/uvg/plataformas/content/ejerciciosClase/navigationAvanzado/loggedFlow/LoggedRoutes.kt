package uvg.plataformas.content.ejerciciosClase.navigationAvanzado.loggedFlow

import kotlinx.serialization.Serializable

sealed interface LoggedRoutes {
    @Serializable
    data object Home : LoggedRoutes

    @Serializable
    data object News : LoggedRoutes
}