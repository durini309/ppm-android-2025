package uvg.plataformas.content.ejerciciosClase

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uvg.plataformas.content.ui.theme.ContentTheme

@Composable
fun BotonCorrecto(
    modifier: Modifier = Modifier
) {
    var counter by rememberSaveable { mutableIntStateOf(0) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: ${counter}")
        Button(
            onClick = {
                counter++
                Log.d("Durini", counter.toString())
            }
        ) {
            Text("incrementar")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewBotonCorrecto() {
    ContentTheme {
        BotonCorrecto(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun NombreDeUsuarioPadre() {
    var username by rememberSaveable { mutableStateOf("") }
    NombreDeUsuario(
        username = username,
        usernameChange = { username = it }
    )
}

@Composable
fun NombreDeUsuario(
    username: String,
    usernameChange: (String) -> Unit = {}, // { username = it }
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = usernameChange, // onValueChange = { username = it }
            placeholder = {
                Text("@jcdurini")
            },
            label = {
                Text("Username")
            },
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewInput() {
    ContentTheme {
        NombreDeUsuario(
            modifier = Modifier.fillMaxSize(),
            username = "",
            usernameChange = {}
        )
    }
}