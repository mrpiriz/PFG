package com.gestion.gestionAlumnos.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Blue40,
    secondary = Blue80
)

@Composable
fun GestionAlumnosTheme(content: @Composable () -> Unit) {
    MaterialTheme(
    colorScheme = LightColors,
    typography = AppTypography,
    content = content
)
}