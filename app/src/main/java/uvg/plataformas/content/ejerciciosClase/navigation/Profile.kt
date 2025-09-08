package uvg.plataformas.content.ejerciciosClase.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object Profile

@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text ="Profile",
            style = MaterialTheme.typography.displayLarge
        )
        OutlinedButton(
            onClick = onNavigateToHome
        ) {
            Text("Cerrar sesion")
        }
    }
}