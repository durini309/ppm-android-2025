package uvg.plataformas.content.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


// Juan Carlos Durini

fun NavGraphBuilder.placesScreen(
    onNavigateToProfile: (Int) -> Unit,
    onNavigateToNewPlace: () -> Unit
) {
    composable<RoomRoutes.PlaceList> {
        PlacesScreen(
            onNavigateToProfile = onNavigateToProfile,
            onNavigateToNewPlace = onNavigateToNewPlace
        )
    }
}

@Composable
fun PlacesScreen(
    onNavigateToProfile: (Int) -> Unit,
    onNavigateToNewPlace: () -> Unit,
    viewModel: PlacesViewModel = viewModel()
) {

    val data by viewModel.placesFlow.collectAsStateWithLifecycle()
    PlacesScreenContent(

        data = data,
        onEditClick = onNavigateToProfile,
        onDeleteClick = { id ->
            viewModel.deletePlace(id)
        },
        onDeleteAllClick = {
            viewModel.deleteAllPlaces()
        },
        onInsertDummyClick = {
            viewModel.insertDummyData()
        },
        onAddClick = onNavigateToNewPlace
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesScreenContent(

    data: List<PlaceSummary>,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onDeleteAllClick: () -> Unit,
    onInsertDummyClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Places") },
                actions = {
                    IconButton(onClick = onDeleteAllClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar todos"
                        )
                    }
                    IconButton(onClick = onInsertDummyClick) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Insertar datos dummy"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar lugar"
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (data.isEmpty()) {
                Text(
                    text = "No hay lugares registrados",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(data) { place ->
                        PlaceItem(
                            place = place,
                            onEditClick = { onEditClick(place.id) },
                            onDeleteClick = { onDeleteClick(place.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlaceItem(
    place: PlaceSummary,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = place.name,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = place.address,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row {
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar"
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar"
                )
            }
        }
    }
}
