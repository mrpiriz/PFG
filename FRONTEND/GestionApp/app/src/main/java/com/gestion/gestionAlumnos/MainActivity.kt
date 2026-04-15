package com.gestion.gestionAlumnos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gestion.gestionAlumnos.ui.navigation.AppNavigation
import com.gestion.gestionAlumnos.ui.theme.GestionAlumnosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionAlumnosTheme {
                AppNavigation()
            }
        }
    }
}