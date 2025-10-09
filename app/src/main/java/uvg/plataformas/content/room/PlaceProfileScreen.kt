package uvg.plataformas.content.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

fun NavGraphBuilder.placeProfileScreen(
    onNavigateBack: () -> Unit
) {
    composable<RoomRoutes.PlaceProfile> { backstack ->
        PlaceProfileScreen(
            onNavigateBack = onNavigateBack
        )
    }
}

@Composable
fun PlaceProfileScreen(
    onNavigateBack: () -> Unit,
    viewModel: PlaceProfileViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PlaceProfileScreenContent(
        state = state,
        onUpdateClick = { place ->
            viewModel.updatePlace(place)
        },
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceProfileScreenContent(
    state: PlaceProfileScreenState,
    onUpdateClick: (PlaceEntity) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Update place") },
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
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator()
                }
                state.isError -> {
                    Text(
                        text = "Error al cargar el lugar",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                state.data == null -> {
                    Text(
                        text = "Lugar no encontrado",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                else -> {
                    PlaceForm(
                        place = state.data,
                        onUpdateClick = onUpdateClick,
                        onNavigateBack = onNavigateBack
                    )
                }
            }
        }
    }
}

@Composable
fun PlaceForm(
    place: PlaceEntity,
    onUpdateClick: (PlaceEntity) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf(place.name) }
    var address by remember { mutableStateOf(place.address) }
    var latitud by remember { mutableFloatStateOf(place.lat) }
    var longitud by remember { mutableFloatStateOf(place.long) }
    var schedule by remember { mutableStateOf(place.schedule) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Editar Lugar",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "ID: ${place.id}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
                val updatedPlace = place.copy(
                    name = name,
                    address = address,
                    lat = latitud,
                    long = longitud,
                    schedule = schedule
                )
                onUpdateClick(updatedPlace)
                onNavigateBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}
