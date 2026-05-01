package com.gestion.gestionAlumnos.ui.screens.asignaturas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gestion.gestionAlumnos.core.utils.SessionManager
import com.gestion.gestionAlumnos.data.model.Asignatura
import com.gestion.gestionAlumnos.ui.theme.GestionAlumnosTheme

@Composable
fun AsignaturaScreen(
    sessionManager: SessionManager,
    viewModel: AsignaturaViewModel = viewModel()
) {
    val asignaturas by viewModel.asignaturas.collectAsState()
    val error by viewModel.error.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarAsignaturas(sessionManager)
    }

    AsignaturaContent(
        asignaturas = asignaturas,
        error = error,
        loading = loading
    )
}

@Composable
fun AsignaturaContent(
    asignaturas: List<Asignatura>,
    error: String?,
    loading: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Listado de asignaturas",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        if (loading) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
                items(asignaturas) { asignatura ->
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
                                text = asignatura.nombre,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = "Horas semanales: ${asignatura.horas_semanales}",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                text = "Descripción: ${asignatura.descripcion ?: "-"}",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AsignaturaScreenPreview() {
    GestionAlumnosTheme {
        AsignaturaContent(
            asignaturas = listOf(
                Asignatura(
                    id_asignatura = 1,
                    nombre = "Programación",
                    descripcion = "Asignatura de desarrollo de software",
                    horas_semanales = 6
                ),
                Asignatura(
                    id_asignatura = 2,
                    nombre = "Bases de Datos",
                    descripcion = "Diseño y gestión de bases de datos relacionales",
                    horas_semanales = 5
                )
            ),
            error = null,
            loading = false
        )
    }
}