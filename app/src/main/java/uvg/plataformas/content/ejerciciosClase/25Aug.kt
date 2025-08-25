package uvg.plataformas.content.ejerciciosClase

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uvg.plataformas.content.ui.theme.ContentTheme

val students = listOf(
    "Juan",
    "Maria",
    "Pedro",
    "Ana",
    "Luis",
    "Sofia",
    "Carlos",
    "Laura",
    "Diego",
    "Juan",
    "Maria",
    "Pedro",
    "Ana",
    "Luis",
    "Sofia",
    "Carlos",
    "Laura",
    "Diego",
)

@Composable
fun StudentList(
    students: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Text(
                text = "Primeros 10",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(students.subList(0, 10)) { student ->
            ContactCard(
                icon = Icons.Filled.Person,
                name = student,
                email = "$student@example.com",
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Text(
                text = "Ultimos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(students.subList(10, students.size - 1)) { student ->
            ContactCard(
                icon = Icons.Filled.Person,
                name = student,
                email = "$student@example.com",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ContactCard(
    icon: ImageVector,
    name: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape
                )
                .padding(8.dp)
        )
        Column {
            Text(name)
            Text(email)
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewContactCard() {
    ContentTheme {
        StudentList(students)

    }
}