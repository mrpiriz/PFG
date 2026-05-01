package com.gestion.gestionAlumnos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gestion.gestionAlumnos.core.utils.SessionManager
import com.gestion.gestionAlumnos.ui.screens.alumnos.AlumnoScreen
import com.gestion.gestionAlumnos.ui.screens.asignaturas.AsignaturaScreen
import com.gestion.gestionAlumnos.ui.screens.calificaciones.CalificacionScreen
import com.gestion.gestionAlumnos.ui.screens.home.HomeScreen
import com.gestion.gestionAlumnos.ui.screens.login.LoginScreen
import com.gestion.gestionAlumnos.ui.screens.profesores.ProfesorScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sessionManager = remember {SessionManager(context.applicationContext)}
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                sessionManager = sessionManager,
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onAlumnosClick = { navController.navigate(Routes.ALUMNOS) },
                onProfesoresClick = { navController.navigate(Routes.PROFESORES) },
                onAsignaturasClick = { navController.navigate(Routes.ASIGNATURAS) },
                onCalificacionesClick = { navController.navigate(Routes.CALIFICACIONES) },
                onLogout = {
                    scope.launch {
                        sessionManager.clearSession()
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Routes.ALUMNOS) {
            AlumnoScreen(sessionManager = sessionManager)
        }

        composable(Routes.PROFESORES) {
            ProfesorScreen(sessionManager = sessionManager)
        }

        composable(Routes.ASIGNATURAS) {
            AsignaturaScreen(sessionManager = sessionManager)
        }

        composable(Routes.CALIFICACIONES) {
            CalificacionScreen(sessionManager = sessionManager)
        }
    }
}