package com.gestion.gestionAlumnos.ui.screens.alumnos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gestion.gestionAlumnos.core.utils.SessionManager
import com.gestion.gestionAlumnos.data.model.Alumno
import com.gestion.gestionAlumnos.ui.theme.GestionAlumnosTheme

@Composable
fun AlumnoScreen(
    sessionManager: SessionManager,
    viewModel: AlumnoViewModel = viewModel()
) {
    val alumnos by viewModel.alumnos.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarAlumnos(sessionManager)
    }

    AlumnoContent(alumnos = alumnos, error = error)
}

@Composable
fun AlumnoContent(
    alumnos: List<Alumno>,
    error: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Listado de alumnos",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
            items(alumnos) { alumno ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "${alumno.nombre} ${alumno.apellidos}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = "DNI: ${alumno.dni}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = "Nacimiento: ${alumno.fecha_nacimiento}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlumnoScreenPreview() {
    GestionAlumnosTheme {
        AlumnoContent(
            alumnos = listOf(
                Alumno(
                    id_alumno = 1,
                    nombre = "Ana",
                    apellidos = "García López",
                    dni = "12345678A",
                    fecha_nacimiento = "2005-03-12"
                )
            ),
            error = null
        )
    }
}