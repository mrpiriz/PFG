package com.gestion.gestionAlumnos.data.repository

import com.gestion.gestionAlumnos.core.network.RetrofitClient
import com.gestion.gestionAlumnos.data.model.Calificacion

class CalificacionRepository {

    suspend fun getCalificaciones(token: String): Result<List<Calificacion>> {
        return try {
            val response = RetrofitClient.apiService.getCalificaciones("Bearer $token")
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error al cargar calificaciones"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}