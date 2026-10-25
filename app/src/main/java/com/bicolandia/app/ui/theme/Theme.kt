package com.bicolandia.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Palette pulled from the Figma exports
val BicolCoral = Color(0xFFE8483A)         // search button, FAB, badges
val BicolDeepGreen = Color(0xFF1E5631)     // profile header, active nav tint
val BicolOrange = Color(0xFFFFC107)        // rating stars / warm badges
val BicolBg = Color(0xFFF6F7F9)
val BicolCardWhite = Color(0xFFFFFFFF)
val BicolTextPrimary = Color(0xFF1B1B1F)
val BicolTextSecondary = Color(0xFF6B7280)

// Old names kept as aliases in case anything else still references them
val BicolRed = BicolCoral
val BicolGreenDark = BicolDeepGreen
val BicolAmber = BicolOrange
val BicolCard = BicolCardWhite
val BicolTextDark = BicolTextPrimary
val BicolTextGray = BicolTextSecondary

private val LightColors = lightColorScheme(
    primary = BicolCoral,
    secondary = BicolDeepGreen,
    background = BicolBg,
    surface = BicolCardWhite,
    onPrimary = Color.White,
    onSecondary = Color.White
)

@Composable
fun BicolandiaTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LightColors, content = content)
}

fun Color.Companion.fromHex(hex: String): Color = Color(android.graphics.Color.parseColor(hex))
