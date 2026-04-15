package com.gestion.gestionAlumnos.ui.screens.alumnos

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gestion.gestionAlumnos.core.utils.SessionManager
import com.gestion.gestionAlumnos.ui.screens.login.LoginScreen


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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Listado de alumnos", style = MaterialTheme.typography.headlineSmall)

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
            items(alumnos) { alumno ->
                Card(modifier = Modifier.padding(vertical = 6.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("${alumno.nombre} ${alumno.apellidos}")
                        Text("DNI: ${alumno.dni}")
                        Text("Nacimiento: ${alumno.fecha_nacimiento}")
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen(
        sessionManager = SessionManager(LocalContext.current),
        onLoginSuccess = {}
    )
}