package uvg.plataformas.content.ejerciciosClase

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.*
import kotlin.random.Random
import kotlinx.coroutines.launch

@OptIn(ExperimentalTextApi::class)
@Composable
fun SpinnerWheel(
    items: List<String>,
    modifier: Modifier = Modifier,
    wheelSize: Int = 300
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    // Estados para la animación
    var isSpinning by remember { mutableStateOf(false) }
    var rotation by remember { mutableStateOf(0f) }

    // Animación de rotación
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = rotation,
        targetValue = rotation + 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "angle"
    )

    // Colores para los segmentos (inspirados en Material3)
    val segmentColors = remember {
        listOf(
            Color(0xFF4CAF50), // Verde
            Color(0xFFE91E63), // Rosa
            Color(0xFF2196F3), // Azul
            Color(0xFFFFC107), // Ámbar
            Color(0xFF9C27B0), // Púrpura
            Color(0xFFFF5722), // Naranja profundo
            Color(0xFF00BCD4), // Cian
            Color(0xFF795548), // Marrón
        )
    }

    // TextMeasurer para medir el texto
    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = modifier
            .size(wheelSize.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Sombra y fondo
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .shadow(8.dp, CircleShape)
                .clip(CircleShape)
                .background(Color.White)
                .rotate(if (isSpinning) angle else rotation)
        ) {
            val radius = size.minDimension / 2
            val center = size.center
            val anglePerSegment = 360f / items.size

            // Dibujar segmentos
            items.forEachIndexed { index, item ->
                val startAngle = index * anglePerSegment - 90f
                val color = segmentColors[index % segmentColors.size]

                // Dibujar el segmento
                drawArc(
                    color = color,
                    startAngle = startAngle,
                    sweepAngle = anglePerSegment,
                    useCenter = true,
                    size = size
                )

                // Dibujar borde blanco entre segmentos
                rotate(startAngle + anglePerSegment) {
                    drawLine(
                        color = Color.White,
                        start = center,
                        end = Offset(center.x, center.y - radius),
                        strokeWidth = 3.dp.toPx()
                    )
                }

                // Dibujar círculos blancos para los premios
                val angleRad = Math.toRadians((startAngle + anglePerSegment / 2).toDouble())
                val textRadius = radius * 0.7f
                val x = center.x + cos(angleRad).toFloat() * textRadius
                val y = center.y + sin(angleRad).toFloat() * textRadius

                // Círculo blanco de fondo para el texto
                drawCircle(
                    color = Color.White,
                    radius = radius * 0.15f,
                    center = Offset(x, y)
                )

                // Dibujar el texto del premio
                val textLayoutResult = textMeasurer.measure(
                    text = AnnotatedString(item),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                )

                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = Offset(
                        x - textLayoutResult.size.width / 2,
                        y - textLayoutResult.size.height / 2
                    )
                )
            }

            // Dibujar borde exterior
            drawCircle(
                color = Color.White,
                radius = radius,
                style = Stroke(width = 4.dp.toPx())
            )
        }

        // Indicador (flecha en la parte superior)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)

        ) {
            Canvas(
                modifier = Modifier.size(30.dp, 40.dp)
            ) {
                val path = Path().apply {
                    moveTo(size.width / 2, 0f)
                    lineTo(0f, size.height * 0.6f)
                    lineTo(size.width, size.height * 0.6f)
                    close()
                }
                drawPath(
                    path = path,
                    color = Color(0xFFFF5722)
                )
                drawPath(
                    path = path,
                    color = Color.White,
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }

        // Botón central para girar
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFE91E63))
                .clickable(enabled = !isSpinning) {
                    coroutineScope.launch {
                        isSpinning = true

                        // Generar rotación aleatoria
                        val randomRotation = Random.nextInt(720, 1080).toFloat()
                        val targetRotation = rotation + randomRotation

                        // Animar la rotación con desaceleración
                        animate(
                            initialValue = rotation,
                            targetValue = targetRotation,
                            animationSpec = tween(
                                durationMillis = 30000,
                                easing = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1f)
                            )
                        ) { value, _ ->
                            rotation = value
                        }

                        // Calcular el premio ganador
                        // El indicador está arriba, ajustamos el cálculo para que coincida
                        val finalAngle = rotation % 360
                        val normalizedAngle = if (finalAngle < 0) finalAngle + 360 else finalAngle
                        val anglePerSegment = 360f / items.size

                        // Como los segmentos empiezan en -90° y el indicador está arriba (0°),
                        // necesitamos ajustar sumando 90° y luego invertir porque la rueda gira
                        val adjustedAngle = (360 - normalizedAngle + 90) % 360

                        // Encontrar el índice del segmento
                        var winningIndex = (adjustedAngle / anglePerSegment).toInt()

                        // Ajuste fino: si está justo en el borde, redondear correctamente
                        if (adjustedAngle % anglePerSegment > anglePerSegment * 0.5) {
                            winningIndex = (winningIndex + 1) % items.size
                        }

                        // El desfase de 2 posiciones que mencionas
                        winningIndex = (winningIndex + items.size - 2) % items.size

                        val winner = items[winningIndex]

                        // Mostrar el resultado
                        Toast.makeText(
                            context,
                            "¡Ganaste: $winner!",
                            Toast.LENGTH_LONG
                        ).show()

                        isSpinning = false
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "GIRAR",
                color = Color.White,
                fontSize = 14.sp,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

// Ejemplo de uso
@Composable
fun SpinnerWheelExample(
    modifier: Modifier = Modifier
) {
    val premios = listOf(
        "100€",
        "50€",
        "200€",
        "10€",
        "500€",
        "25€",
        "75€",
        "150€"
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ruleta de Premios",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        SpinnerWheel(
            items = premios,
            wheelSize = 320
        )

        Text(
            text = "Toca el botón central para girar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}