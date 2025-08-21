package uvg.plataformas.content.ejerciciosClase

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uvg.plataformas.content.ui.theme.ContentTheme

@Composable
fun Pantalla1(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Default.Settings,
                contentDescription = "Settings"
            )
            Text(
                text = "Año"
            )
            Icon(
                Icons.Default.Share,
                contentDescription = "Share"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(700.dp)
                .background(
                    Color.Gray
                )
        )
        Text(
            text = "5% completado",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Pantalla2(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lifedots",
                    style = MaterialTheme.typography.headlineLarge
                )
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = "Calendar"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                HomeSectionButton(
                    icon = Icons.Default.FavoriteBorder,
                    text = "Favoritos",
                    modifier = Modifier.weight(1f)
                )
                HomeSectionButton(
                    icon = Icons.Default.Star,
                    text = "Cuenta atrás",
                    modifier = Modifier.weight(1f)
                )
                HomeSectionButton(
                    icon = Icons.Default.DateRange,
                    text = "Hábitos",
                    modifier = Modifier.weight(1f)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HomeCard(
                    title = "Viaje a europa",
                    subtitle = "72 dias restantes",
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                HomeCard(
                    title = "Fin de semestre",
                    subtitle = "98 días restantes",
                    color = MaterialTheme.colorScheme.errorContainer
                )
            }
        }
    }
}

@Composable
fun HomeSectionButton(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Text(text)
    }
}

@Composable
fun HomeCard(
    title: String,
    subtitle: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color)
            )
            Text(
                text = subtitle,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantalla1() {
    ContentTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Pantalla1(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantalla2() {
    ContentTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Pantalla2(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}