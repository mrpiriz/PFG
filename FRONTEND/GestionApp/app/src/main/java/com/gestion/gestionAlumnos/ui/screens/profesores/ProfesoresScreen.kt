package com.gestion.gestionAlumnos.ui.screens.profesores

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
import com.gestion.gestionAlumnos.data.model.Profesor
import com.gestion.gestionAlumnos.ui.theme.GestionAlumnosTheme

@Composable
fun ProfesorScreen(
    sessionManager: SessionManager,
    viewModel: ProfesorViewModel = viewModel()
) {
    val profesores by viewModel.profesores.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarProfesores(sessionManager)
    }

    ProfesorContent(
        profesores = profesores,
        error = error
    )
}

@Composable
fun ProfesorContent(
    profesores: List<Profesor>,
    error: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Listado de profesores",
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
            items(profesores) { profesor ->
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
                            text = "${profesor.nombre} ${profesor.apellidos}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = "DNI: ${profesor.dni}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = "Departamento: ${profesor.departamento}",
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
fun ProfesorScreenPreview() {
    GestionAlumnosTheme {
        ProfesorContent(
            profesores = listOf(
                Profesor(
                    id_profesor = 1,
                    nombre = "Carlos",
                    apellidos = "Martínez Ruiz",
                    dni = "87654321B",
                    departamento = "Informática"
                )
            ),
            error = null
        )
    }
}