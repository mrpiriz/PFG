package com.gestion.gestionAlumnos.ui.screens.profesores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gestion.gestionAlumnos.core.utils.SessionManager
import com.gestion.gestionAlumnos.data.model.Profesor
import com.gestion.gestionAlumnos.data.repository.ProfesorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfesorViewModel : ViewModel() {

    private val repository = ProfesorRepository()

    private val _profesores = MutableStateFlow<List<Profesor>>(emptyList())
    val profesores: StateFlow<List<Profesor>> = _profesores

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun cargarProfesores(sessionManager: SessionManager) {
        viewModelScope.launch {
            val token = sessionManager.getToken()
            if (token == null) {
                _error.value = "No hay sesión"
                return@launch
            }

            val result = repository.getProfesores(token)
            result.onSuccess {
                _profesores.value = it
            }.onFailure {
                _error.value = it.message
            }
        }
    }
}