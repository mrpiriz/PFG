package com.gestion.gestionAlumnos.data.repository

import com.gestion.gestionAlumnos.core.network.RetrofitClient
import com.gestion.gestionAlumnos.data.model.Alumno

class AlumnoRepository {

    suspend fun getAlumnos(token: String): Result<List<Alumno>> {
        return try {
            val response = RetrofitClient.apiService.getAlumnos("Bearer $token")
            val body = response.body()

            if (response.isSuccessful && body != null) {
                Result.success(body)
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Error desconocido"
                Result.failure(Exception("Error ${response.code()}: $errorMsg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}