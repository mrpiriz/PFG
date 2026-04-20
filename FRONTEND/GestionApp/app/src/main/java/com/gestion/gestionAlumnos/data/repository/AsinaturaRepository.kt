package com.gestion.gestionAlumnos.data.repository

import com.gestion.gestionAlumnos.core.network.RetrofitClient
import com.gestion.gestionAlumnos.data.model.Asignatura

class AsignaturaRepository {

    suspend fun getAsignaturas(token: String): Result<List<Asignatura>> {
        return try {
            val response = RetrofitClient.apiService.getAsignaturas("Bearer $token")
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