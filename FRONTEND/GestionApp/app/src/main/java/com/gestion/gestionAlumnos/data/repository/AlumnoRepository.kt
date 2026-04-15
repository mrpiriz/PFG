package com.gestion.gestionAlumnos.data.repository

import com.gestion.gestionAlumnos.core.network.RetrofitClient
import com.gestion.gestionAlumnos.data.model.Alumno

class AlumnoRepository {

    suspend fun getAlumnos(token: String): Result<List<Alumno>> {
        return try {
            val response = RetrofitClient.apiService.getAlumnos("Bearer $token")
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error al cargar alumnos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}