package com.gestion.gestionAlumnos.data.repository

import com.gestion.gestionAlumnos.core.network.RetrofitClient
import com.gestion.gestionAlumnos.data.model.Profesor

class ProfesorRepository {

    suspend fun getProfesores(token: String): Result<List<Profesor>> {
        return try {
            val response = RetrofitClient.apiService.getProfesores("Bearer $token")
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error al cargar profesores"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}