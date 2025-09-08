package uvg.plataformas.content.ejerciciosClase.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login,
        modifier = modifier
    ) {
        composable<Login> {
            LoginScreen(
                onLoginClick = { name ->
                    navController.navigate(
                        Dashboard(
                            name = name
                        )
                    ) {
                        popUpTo<Login> {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Dashboard> { backStackEntry ->
            val destination = backStackEntry.toRoute<Dashboard>()
            DashboardScreen(
                name = destination.name,
                onNavigateToProfile = {
                    navController.navigate(Profile)
                }
            )
        }

        composable<Profile> {
            ProfileScreen(
                onNavigateToHome = {
                    navController.navigate(Login) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}