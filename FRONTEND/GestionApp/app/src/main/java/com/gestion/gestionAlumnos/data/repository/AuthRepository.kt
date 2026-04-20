package com.gestion.gestionAlumnos.data.repository

import com.gestion.gestionAlumnos.core.network.RetrofitClient
import com.gestion.gestionAlumnos.data.model.LoginRequest
import com.gestion.gestionAlumnos.data.model.LoginResponse

class AuthRepository {

    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val response = RetrofitClient.apiService.login(LoginRequest(email, password))
            val body = response.body()

            if (response.isSuccessful && body != null) {
                Result.success(body)
            } else {
                val errorMsg = response.errorBody()?.string()

                when (response.code()) {
                    401 -> Result.failure(Exception("Credenciales incorrectas"))
                    500 -> Result.failure(Exception("Error del servidor"))
                    else -> Result.failure(
                        Exception(errorMsg ?: "Error ${response.code()} al iniciar sesión")
                    )
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}