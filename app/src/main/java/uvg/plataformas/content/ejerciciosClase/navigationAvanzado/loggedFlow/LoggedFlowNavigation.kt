package uvg.plataformas.content.ejerciciosClase.navigationAvanzado.loggedFlow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uvg.plataformas.content.ejerciciosClase.navigationAvanzado.MainRoutes

/**
 * Extension function de NavGraphBuilder para registrar la pantalla del flujo loggeado
 * en el NavHost principal. Esta función actúa como punto de entrada al sistema de
 * navegación anidada del flujo post-login.
 */
fun NavGraphBuilder.loggedFlowScreen() {
    composable<MainRoutes.LoggedFlow> {
        LoggedFlowContent(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

/**
 * Composable principal del flujo loggeado que contiene la estructura de navegación
 * interna con TopBar, BottomBar y el NavHost para las pantallas secundarias.
 * 
 * @param modifier Modificador para aplicar al contenedor principal
 */
@Composable
fun LoggedFlowContent(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    
    // Observa cambios en el back stack para obtener el destino actual
    // currentBackStackEntryAsState() devuelve un State que se recompone automáticamente
    // cuando cambia la navegación, permitiendo actualizar la UI reactivamente
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(
                onNavigate = { route ->
                    navController.navigate(route)
                },
                currentDestination = currentDestination
            )
        }
    ) { paddingValues ->
        // NavHost interno para manejar la navegación entre Home y News
        NavHost(
            navController = navController,
            startDestination = LoggedRoutes.Home,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<LoggedRoutes.Home> {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            composable<LoggedRoutes.News> {
                NewsScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
        }
    }
}

/**
 * Barra de navegación inferior que muestra los tabs disponibles.
 * Utiliza el destino actual para resaltar el tab seleccionado.
 * 
 * @param onNavigate Callback que se ejecuta cuando se selecciona un tab
 * @param currentDestination Destino actual para determinar qué tab está seleccionado
 */
@Composable
private fun BottomBar(
    onNavigate: (LoggedRoutes) -> Unit,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                // Compara el nombre de la clase de la ruta con el route del destino actual
                // para determinar si este item está seleccionado
                selected = item.destination::class.qualifiedName == currentDestination?.route,
                onClick = { onNavigate(item.destination) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}

/**
 * Barra superior de la aplicación que muestra el título del flujo loggeado.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        title = {
            Text("Logged Flow")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    )
}

/**
 * Lista de items de navegación que se muestran en la barra inferior.
 */
private val bottomNavItems =
    listOf(
        NavigationEntry.Home,
        NavigationEntry.News,
    )

/**
 * Clase sellada que representa las entradas de navegación disponibles en la barra inferior.
 * Cada entrada contiene la información necesaria para mostrar el tab y navegar al destino.
 * 
 * @param title Texto que se muestra como etiqueta del tab
 * @param icon Icono que se muestra en el tab
 * @param destination Ruta de destino asociada al tab
 */
sealed class NavigationEntry(
    var title: String,
    var icon: ImageVector,
    var destination: LoggedRoutes,
) {
    /**
     * Entrada de navegación para la pantalla de inicio.
     */
    data object Home : NavigationEntry(
        title = "Inicio",
        icon = Icons.Default.Home,
        destination = LoggedRoutes.Home,
    )

    /**
     * Entrada de navegación para la pantalla de noticias.
     */
    data object News : NavigationEntry(
        title = "Noticias",
        icon = Icons.Default.Email,
        destination = LoggedRoutes.News,
    )
}