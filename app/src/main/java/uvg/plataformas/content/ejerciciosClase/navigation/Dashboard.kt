package uvg.plataformas.content.ejerciciosClase.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data class Dashboard(
    val name: String
)

@Composable
fun DashboardScreen(
    name: String,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text ="Dashboard",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedButton(
            onClick = onNavigateToProfile
        ) {
            Text("Ir a perfil")
        }
    }
}