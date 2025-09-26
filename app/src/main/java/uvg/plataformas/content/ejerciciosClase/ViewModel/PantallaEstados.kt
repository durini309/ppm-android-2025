package uvg.plataformas.content.ejerciciosClase.ViewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uvg.plataformas.content.ui.theme.ContentTheme

@Composable
fun PantallaEstados(
    viewModel: EstadosViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ContenidoPantallaEventos(
        isLoading = state.isLoading,
        isSuccess = state.isSuccess,
        isError = state.isError,
        winnerNumber = state.winnerNumber,
        cambiarEstadoClick = { viewModel.cambiarEstado() },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ContenidoPantallaEventos(
    isLoading: Boolean,
    isSuccess: Boolean,
    isError: Boolean,
    winnerNumber: Int,
    cambiarEstadoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
    ) {
        when {
            isLoading -> PantallaLoading(
                modifier = Modifier.align(Alignment.Center),
                cambiarEstadoClick = cambiarEstadoClick
            )
            isSuccess -> PantallaSuccess(
                winnerNumber = winnerNumber,
                modifier = Modifier.align(Alignment.Center),
                cambiarEstadoClick = cambiarEstadoClick
            )
            isError -> PantallaError(
                modifier = Modifier.align(Alignment.Center),
                cambiarEstadoClick = cambiarEstadoClick
            )
        }
    }
}

@Composable
private fun PantallaLoading(
    cambiarEstadoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        CircularProgressIndicator(
            modifier = modifier
        )
        Text(text = "Cargando...")
    }
}

@Composable
private fun PantallaSuccess(
    winnerNumber: Int,
    cambiarEstadoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Success: $winnerNumber",
            style = MaterialTheme.typography.displayLarge,
            color = Color.Green
        )
        Button(
            onClick = cambiarEstadoClick
        ) {
            Text(text = "Volver a empezar")
        }
    }
}

@Composable
private fun PantallaError(
    cambiarEstadoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Error",
            style = MaterialTheme.typography.displayLarge,
            color = Color.Red
        )
        Button(
            onClick = cambiarEstadoClick
        ) {
            Text(text = "Reintentar")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEstados() {
    ContentTheme {
        PantallaEstados()
    }
}