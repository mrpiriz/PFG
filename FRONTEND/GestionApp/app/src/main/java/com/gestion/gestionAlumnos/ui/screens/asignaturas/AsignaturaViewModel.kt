package com.gestion.gestionAlumnos.ui.screens.asignaturas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gestion.gestionAlumnos.core.utils.SessionManager
import com.gestion.gestionAlumnos.data.model.Asignatura
import com.gestion.gestionAlumnos.data.repository.AsignaturaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AsignaturaViewModel : ViewModel() {

    private val repository = AsignaturaRepository()

    private val _asignaturas = MutableStateFlow<List<Asignatura>>(emptyList())
    val asignaturas: StateFlow<List<Asignatura>> = _asignaturas

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun cargarAsignaturas(sessionManager: SessionManager) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            val token = sessionManager.getToken()
            if (token == null) {
                _error.value = "No hay sesión"
                _loading.value = false
                return@launch
            }

            val result = repository.getAsignaturas(token)
            result.onSuccess {
                _asignaturas.value = it
                _error.value = null
            }.onFailure {
                _error.value = it.message ?: "Error al cargar asignaturas"
            }

            _loading.value = false
        }
    }
}