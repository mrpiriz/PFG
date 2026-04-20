package com.gestion.gestionAlumnos.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gestion.gestionAlumnos.core.utils.SessionManager
import com.gestion.gestionAlumnos.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String, sessionManager: SessionManager) {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) {
                _uiState.value = LoginUiState.Error("Email y contraseña son obligatorios")
                return@launch
            }

            _uiState.value = LoginUiState.Loading

            val result = repository.login(email, password)
            result.onSuccess { response ->
                try {
                    sessionManager.saveSession(
                        token = response.token,
                        rol = response.usuario.rol,
                        email = response.usuario.email
                    )
                    _uiState.value = LoginUiState.Success
                } catch (e: Exception) {
                    _uiState.value = LoginUiState.Error("No se pudo guardar la sesión")
                }
            }.onFailure {
                _uiState.value = LoginUiState.Error(it.message ?: "Error desconocido")
            }
        }
    }
}

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}