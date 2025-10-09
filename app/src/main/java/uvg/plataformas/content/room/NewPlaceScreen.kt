package uvg.plataformas.content.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.newPlaceScreen(
    onNavigateBack: () -> Unit
) {
    composable<RoomRoutes.NewPlace> {
        NewPlaceScreen(
            onNavigateBack = onNavigateBack
        )
    }
}

@Composable
fun NewPlaceScreen(
    onNavigateBack: () -> Unit,
    viewModel: NewPlaceViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NewPlaceScreenContent(
        state = state,
        onCreateClick = { place ->
            viewModel.insertPlace(place)
        },
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPlaceScreenContent(
    state: NewPlaceScreenState,
    onCreateClick: (PlaceEntity) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create place") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        NewPlaceForm(
            onCreateClick = onCreateClick,
            onNavigateBack = onNavigateBack,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun NewPlaceForm(
    onCreateClick: (PlaceEntity) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var latitud by remember { mutableFloatStateOf(0f) }
    var longitud by remember { mutableFloatStateOf(0f) }
    var schedule by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Nuevo Lugar",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = latitud.toString(),
            onValueChange = { latitud = it.toFloatOrNull() ?: latitud },
            label = { Text("Latitud") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = longitud.toString(),
            onValueChange = { longitud = it.toFloatOrNull() ?: longitud },
            label = { Text("Longitud") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = schedule,
            onValueChange = { schedule = it },
            label = { Text("Horario") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val newPlace = PlaceEntity(
                    name = name,
                    address = address,
                    lat = latitud,
                    long = longitud,
                    schedule = schedule
                )
                onCreateClick(newPlace)
                onNavigateBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear")
        }
    }
}
