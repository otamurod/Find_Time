package uz.otamurod.kmp.findtime.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors().copy(
            primary = primaryDarkColor,
            primaryVariant = primaryLightColor,
            secondary = secondaryDarkColor,
            secondaryVariant = secondaryLightColor,
            onPrimary = Color.White,
        )
    } else {
        lightColors().copy(
            primary = primaryColor,
            primaryVariant = primaryLightColor,
            secondary = secondaryColor,
            secondaryVariant = secondaryLightColor,
            onPrimary = Color.Black,
        )
    }
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
