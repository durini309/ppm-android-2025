package uvg.plataformas.content.ejerciciosClase.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

@Serializable
data object Login

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (String) -> Unit,
) {
    var name by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text ="Login",
            style = MaterialTheme.typography.displayLarge
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Ingrese nombre") }
        )
        Button(onClick = {
            onLoginClick(name)
        }) {
           Text("Ir a dashboard")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    LoginScreen(
        onLoginClick = {},
        modifier = Modifier.fillMaxSize()
    )
}