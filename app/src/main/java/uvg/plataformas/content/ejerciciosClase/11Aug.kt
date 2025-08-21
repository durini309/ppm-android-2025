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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uvg.plataformas.content.ui.theme.ContentTheme

@Preview(showBackground = true)
@Composable
private fun EjemploOrdenModifiers() {
    ContentTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red.copy(alpha = 0.4f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color.Red)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Black)
                            .align(Alignment.Center)
                            .border(
                                width = 4.dp,
                                color = Color.White
                            )
                            .padding(20.dp)
                            .border(
                                width = 4.dp,
                                color = Color.White
                            )
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color.Blue)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .border(
                                width = 4.dp,
                                color = Color.White
                            )
                            .padding(20.dp)
                            .background(Color.Black)
                            .align(Alignment.Center)
                            .border(
                                width = 4.dp,
                                color = Color.White
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ResolucionImagenPlatz() {
    ContentTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null
                    )
                }
                Text(
                    text = "Clinica Dr Shan",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal =16.dp, vertical = 8.dp)
                )
                Text(
                    text = "1234 Main St, Anytown USA",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(horizontal =16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal =16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BadgeEspecialidad(text = "Cardiología")
                    BadgeEspecialidad(text = "Uroilogia pediatrica")
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.1f),
                        contentColor = Color.Red
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal =16.dp)
                ) {
                    Text("Iniciar visita")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(200.dp)
                        .background(Color.Gray)

                )
                Text(
                    text = "Informacion adicional",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal =16.dp, vertical = 8.dp),
                )
                Column(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    AtributoClinica(
                        icon = Icons.Default.DateRange,
                        nombre = "Fecha de nacimiento",
                        valor = "30/09/1994",
                        modifier = Modifier
                    )
                    AtributoClinica(
                        icon = Icons.Default.AccountBox,
                        nombre = "Colegiado",
                        valor = "1234",
                        modifier = Modifier
                    )
                    AtributoClinica(
                        icon = Icons.Default.Phone,
                        nombre = "Telefono",
                        valor = "1231231231",
                        modifier = Modifier
                    )
                }

            }
        }
    }
}

@Composable
fun BadgeEspecialidad(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .background(Color.Blue.copy(alpha = 0.2f))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        style = MaterialTheme.typography.labelSmall,
        color = Color.Blue
    )
}

@Composable
fun AtributoClinica(
    icon: ImageVector,
    nombre: String,
    valor: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Column {
            Text(
                text = nombre
            )
            Text(
                text = valor
            )
        }
    }
}