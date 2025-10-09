package uvg.plataformas.content.room

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import uvg.plataformas.content.ui.theme.ContentTheme

sealed interface RoomRoutes {
    @Serializable
    data object PlaceList : RoomRoutes

    @Serializable
    data class PlaceProfile(val id: Int) : RoomRoutes

    @Serializable
    data object NewPlace : RoomRoutes
}

@Composable
fun RoomNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RoomRoutes.PlaceList,
        modifier = modifier
    ) {
        placesScreen(
            onNavigateToProfile = { id ->
                navController.navigate(RoomRoutes.PlaceProfile(id))
            },
            onNavigateToNewPlace = {
                navController.navigate(RoomRoutes.NewPlace)
            }
        )

        placeProfileScreen(
            onNavigateBack = {
                navController.navigateUp()
            }
        )

        newPlaceScreen(
            onNavigateBack = {
                navController.navigateUp()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RoomNavigationPreview() {
    ContentTheme {
        Surface {
            RoomNavigation(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
