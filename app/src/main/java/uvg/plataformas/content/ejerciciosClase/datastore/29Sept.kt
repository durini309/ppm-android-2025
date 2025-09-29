package uvg.plataformas.content.ejerciciosClase.datastore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uvg.plataformas.content.ui.theme.ContentTheme


@Composable
fun DataStoreScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = viewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle()
    val flagState by viewModel.loggedInState.collectAsStateWithLifecycle()

    DataStoreScreenContent(
        isLoggedIn = isLoggedIn,
        flagState = flagState,
        onGetFlag = { viewModel.getLoggedInFlag() },
        onLogin = { viewModel.setLoggedIn(true) },
        onLogout = { viewModel.setLoggedIn(false) },
        modifier = modifier
    )
}

@Composable
fun DataStoreScreenContent(
    isLoggedIn: Boolean,
    flagState: Boolean?,
    onGetFlag: () -> Unit,
    onLogin: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Status row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Status: ",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = if (isLoggedIn) "loggeado" else "no loggeado",
                style = MaterialTheme.typography.bodyLarge,
                color = if (isLoggedIn) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }

        HorizontalDivider()

        // Flag section
        Button(
            onClick = onGetFlag,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Obtener flag")
        }

        Text(
            text = "Flag: ${when(flagState) {
                true -> "loggeado"
                false -> "desloggeado"
                null -> "-"
            }}",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider()

        // Login/Logout buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = onLogin,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text("Loggear")
            }

            Button(
                onClick = onLogout,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Text("Desloggear")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DataStoreScreenContentPreview() {
    ContentTheme {
        Surface {
            DataStoreScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            )
        }
    }
}

