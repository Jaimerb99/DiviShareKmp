package com.jrb.divishare.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

// === BRAND (Menta DiviShare - Usando tu verde original) ===
val MintPrimary = Color(0xFF52C7A4)
val MintOnPrimary = Color(0xFFFFFFFF)
val MintContainer = Color(0xFFD4F5E6)
val MintOnContainer = Color(0xFF0F5135)

val MintPrimaryDark = Color(0xFF6EE7B7)
val MintOnPrimaryDark = Color(0xFF042F1C)
val MintContainerDark = Color(0xFF0D5F3A)
val MintOnContainerDark = Color(0xFFD4F5E6)

// === SEMÁNTICOS (Alertas, Estados, Errores) ===
val ErrorCore = Color(0xFFEF4444)
val ErrorContainer = Color(0xFFFEE2E2)
val ErrorCoreDark = Color(0xFFFCA5A5)
val ErrorContainerDark = Color(0xFF7F1D1D)

val WarningCore = Color(0xFFF59E0B)
val WarningContainer = Color(0xFFFEF3C7)
val WarningCoreDark = Color(0xFFFCD34D)
val WarningContainerDark = Color(0xFF78350F)

val InfoCore = Color(0xFF3B82F6)
val InfoContainer = Color(0xFFDBEAFE)
val InfoCoreDark = Color(0xFF93C5FD)
val InfoContainerDark = Color(0xFF1E3A8A)

val SuccessCore = Color(0xFF10B981)
val SuccessContainer = Color(0xFFD1FAE5)
val SuccessCoreDark = Color(0xFF6EE7B7)
val SuccessContainerDark = Color(0xFF064E3B)

// === NEUTROS / ESCALA DE GRISES ===
val Gray50 = Color(0xFFF8FAFC)
val Gray100 = Color(0xFFF1F5F9)
val Gray200 = Color(0xFFE2E8F0)
val Gray300 = Color(0xFFCBD5E1)
val Gray400 = Color(0xFF94A3B8)
val Gray500 = Color(0xFF64748B)
val Gray600 = Color(0xFF475569)
val Gray800 = Color(0xFF1E293B)
val Gray900 = Color(0xFF0F172A)

// ============================================================================
// === PREVIEW PARA VER LA PALETA DE COLORES EN ANDROID STUDIO ================
// ============================================================================

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 850, heightDp = 1000)
@Composable
fun ColorPalettePreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text("🎨 Paleta de DiviShare", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

                // Fila principal: Claro a la izquierda, Oscuro a la derecha
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // COLUMNA IZQUIERDA (LIGHT)
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        ColorGroup("Mint (Brand) Light", listOf(
                            "MintPrimary" to MintPrimary, "MintOnPrimary" to MintOnPrimary,
                            "MintContainer" to MintContainer, "MintOnContainer" to MintOnContainer,
                        ))

                        ColorGroup("Semánticos (Light)", listOf(
                            "ErrorCore" to ErrorCore, "ErrorContainer" to ErrorContainer,
                            "WarningCore" to WarningCore, "WarningContainer" to WarningContainer,
                            "InfoCore" to InfoCore, "InfoContainer" to InfoContainer,
                            "SuccessCore" to SuccessCore, "SuccessContainer" to SuccessContainer,
                        ))
                    }

                    // COLUMNA DERECHA (DARK)
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        ColorGroup("Mint (Brand) Dark", listOf(
                            "MintPrimaryDark" to MintPrimaryDark, "MintOnPrimaryDark" to MintOnPrimaryDark,
                            "MintContainerDark" to MintContainerDark, "MintOnContainerDark" to MintOnContainerDark,
                        ))

                        ColorGroup("Semánticos (Dark)", listOf(
                            "ErrorCoreDark" to ErrorCoreDark, "ErrorContainerDark" to ErrorContainerDark,
                            "WarningCoreDark" to WarningCoreDark, "WarningContainerDark" to WarningContainerDark,
                            "InfoCoreDark" to InfoCoreDark, "InfoContainerDark" to InfoContainerDark,
                            "SuccessCoreDark" to SuccessCoreDark, "SuccessContainerDark" to SuccessContainerDark,
                        ))
                    }
                }

                Divider(color = Gray200, thickness = 2.dp)

                // GRISES (Ocupando todo el ancho pero distribuidos en 3 columnas internamente)
                ColorGroup("Grises Neutros", listOf(
                    "Gray50" to Gray50, "Gray100" to Gray100, "Gray200" to Gray200,
                    "Gray300" to Gray300, "Gray400" to Gray400, "Gray500" to Gray500,
                    "Gray600" to Gray600, "Gray800" to Gray800, "Gray900" to Gray900,
                ), columns = 3)
            }
        }
    }
}

@Composable
private fun ColorGroup(title: String, colors: List<Pair<String, Color>>, columns: Int = 1) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)

        // Magia para dividir la lista en las columnas que queramos
        colors.chunked(columns).forEach { rowColors ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowColors.forEach { (name, color) ->
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(36.dp)
                                .background(color)
                        )
                    }
                }
                // Rellenar espacios vacíos si la última fila no tiene suficientes colores
                repeat(columns - rowColors.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}