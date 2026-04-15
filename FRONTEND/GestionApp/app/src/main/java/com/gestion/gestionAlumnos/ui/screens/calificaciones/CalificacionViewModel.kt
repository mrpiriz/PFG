package com.gestion.gestionAlumnos.ui.screens.calificaciones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gestion.gestionAlumnos.core.utils.SessionManager
import com.gestion.gestionAlumnos.data.model.Calificacion
import com.gestion.gestionAlumnos.data.repository.CalificacionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalificacionViewModel : ViewModel() {

    private val repository = CalificacionRepository()

    private val _calificaciones = MutableStateFlow<List<Calificacion>>(emptyList())
    val calificaciones: StateFlow<List<Calificacion>> = _calificaciones

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun cargarCalificaciones(sessionManager: SessionManager) {
        viewModelScope.launch {
            val token = sessionManager.getToken()
            if (token == null) {
                _error.value = "No hay sesión"
                return@launch
            }

            val result = repository.getCalificaciones(token)
            result.onSuccess {
                _calificaciones.value = it
            }.onFailure {
                _error.value = it.message
            }
        }
    }
}