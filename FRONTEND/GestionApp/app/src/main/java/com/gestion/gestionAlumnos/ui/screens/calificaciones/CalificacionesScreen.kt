package com.gestion.gestionAlumnos.ui.screens.calificaciones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gestion.gestionAlumnos.core.utils.SessionManager

@Composable
fun CalificacionScreen(
    sessionManager: SessionManager,
    viewModel: CalificacionViewModel = viewModel()
) {
    val calificaciones by viewModel.calificaciones.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarCalificaciones(sessionManager)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Listado de calificaciones", style = MaterialTheme.typography.headlineSmall)

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
            items(calificaciones) { calificacion ->
                Card(modifier = Modifier.padding(vertical = 6.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Alumno: ${calificacion.alumno_nombre} ${calificacion.alumno_apellidos}")
                        Text("Profesor: ${calificacion.profesor_nombre} ${calificacion.profesor_apellidos}")
                        Text("Asignatura: ${calificacion.asignatura_nombre}")
                        Text("Nota: ${calificacion.nota}")
                        Text("Fecha: ${calificacion.fecha}")
                        Text("Observación: ${calificacion.observacion ?: "-"}")
                    }
                }
            }
        }
    }
}