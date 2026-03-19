package com.jrb.divishare.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.jrb.divishare.ui.theme.ErrorContainer
import com.jrb.divishare.ui.theme.ErrorContainerDark
import com.jrb.divishare.ui.theme.ErrorCore
import com.jrb.divishare.ui.theme.ErrorCoreDark
import com.jrb.divishare.ui.theme.Gray100
import com.jrb.divishare.ui.theme.Gray200
import com.jrb.divishare.ui.theme.Gray400
import com.jrb.divishare.ui.theme.Gray50
import com.jrb.divishare.ui.theme.Gray600
import com.jrb.divishare.ui.theme.Gray800
import com.jrb.divishare.ui.theme.Gray900
import com.jrb.divishare.ui.theme.InfoContainer
import com.jrb.divishare.ui.theme.InfoContainerDark
import com.jrb.divishare.ui.theme.InfoCore
import com.jrb.divishare.ui.theme.InfoCoreDark
import com.jrb.divishare.ui.theme.MintContainer
import com.jrb.divishare.ui.theme.MintContainerDark
import com.jrb.divishare.ui.theme.MintOnContainer
import com.jrb.divishare.ui.theme.MintOnContainerDark
import com.jrb.divishare.ui.theme.MintOnPrimary
import com.jrb.divishare.ui.theme.MintOnPrimaryDark
import com.jrb.divishare.ui.theme.MintPrimary
import com.jrb.divishare.ui.theme.MintPrimaryDark
import com.jrb.divishare.ui.theme.SuccessContainer
import com.jrb.divishare.ui.theme.SuccessContainerDark
import com.jrb.divishare.ui.theme.SuccessCore
import com.jrb.divishare.ui.theme.SuccessCoreDark
import com.jrb.divishare.ui.theme.WarningContainer
import com.jrb.divishare.ui.theme.WarningContainerDark
import com.jrb.divishare.ui.theme.WarningCore
import com.jrb.divishare.ui.theme.WarningCoreDark

// 1. Definimos nuestra clase de colores extendida
@Immutable
data class DiviShareExtendedColors(
    val warning: Color,
    val warningContainer: Color,
    val info: Color,
    val infoContainer: Color,
    val success: Color,
    val successContainer: Color,
    val positiveBalance: Color,
    val negativeBalance: Color,
    val divider: Color
)

// 2. Creamos los LocalProviders para que Compose los entienda
val LocalDiviShareColors = staticCompositionLocalOf {
    DiviShareExtendedColors(
        warning = Color.Unspecified, warningContainer = Color.Unspecified,
        info = Color.Unspecified, infoContainer = Color.Unspecified,
        success = Color.Unspecified, successContainer = Color.Unspecified,
        positiveBalance = Color.Unspecified, negativeBalance = Color.Unspecified,
        divider = Color.Unspecified
    )
}

// 3. Mapeo de Material 3 (Light)
private val LightColorScheme = lightColorScheme(
    primary = MintPrimary,
    onPrimary = MintOnPrimary,
    primaryContainer = MintContainer,
    onPrimaryContainer = MintOnContainer,
    background = Gray50,
    onBackground = Gray800,
    surface = Color.White,
    onSurface = Gray800,
    surfaceVariant = Gray100,
    onSurfaceVariant = Gray600,
    error = ErrorCore,
    onError = Color.White,
    errorContainer = ErrorContainer,
    onErrorContainer = ErrorCore
)

private val LightExtendedColors = DiviShareExtendedColors(
    warning = WarningCore, warningContainer = WarningContainer,
    info = InfoCore, infoContainer = InfoContainer,
    success = SuccessCore, successContainer = SuccessContainer,
    positiveBalance = MintPrimary, // Si te deben, sale en verde menta
    negativeBalance = ErrorCore,   // Si debes, sale en rojo
    divider = Gray200
)

// 4. Mapeo de Material 3 (Dark)
private val DarkColorScheme = darkColorScheme(
    primary = MintPrimaryDark,
    onPrimary = MintOnPrimaryDark,
    primaryContainer = MintContainerDark,
    onPrimaryContainer = MintOnContainerDark,
    background = Gray900,
    onBackground = Gray50,
    surface = Gray800,
    onSurface = Gray50,
    surfaceVariant = Gray900,
    onSurfaceVariant = Gray400,
    error = ErrorCoreDark,
    onError = Gray900,
    errorContainer = ErrorContainerDark,
    onErrorContainer = ErrorCoreDark
)

private val DarkExtendedColors = DiviShareExtendedColors(
    warning = WarningCoreDark, warningContainer = WarningContainerDark,
    info = InfoCoreDark, infoContainer = InfoContainerDark,
    success = SuccessCoreDark, successContainer = SuccessContainerDark,
    positiveBalance = MintPrimaryDark,
    negativeBalance = ErrorCoreDark,
    divider = Gray600
)

// 5. Nuestro Theme Wrapper
@Composable
fun DiviShareTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(LocalDiviShareColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

// 6. Un atajo elegante para acceder a tus colores personalizados desde cualquier lado
object DiviShareTheme {
    val colors: DiviShareExtendedColors
        @Composable
        get() = LocalDiviShareColors.current
}