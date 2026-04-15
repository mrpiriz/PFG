package com.gestion.gestionAlumnos.ui.screens.alumnos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gestion.gestionAlumnos.core.utils.SessionManager
import com.gestion.gestionAlumnos.data.model.Alumno
import com.gestion.gestionAlumnos.data.repository.AlumnoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlumnoViewModel : ViewModel() {

    private val repository = AlumnoRepository()

    private val _alumnos = MutableStateFlow<List<Alumno>>(emptyList())
    val alumnos: StateFlow<List<Alumno>> = _alumnos

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun cargarAlumnos(sessionManager: SessionManager) {
        viewModelScope.launch {
            val token = sessionManager.getToken()
            if (token == null) {
                _error.value = "No hay sesión"
                return@launch
            }

            val result = repository.getAlumnos(token)
            result.onSuccess {
                _alumnos.value = it
            }.onFailure {
                _error.value = it.message
            }
        }
    }
}