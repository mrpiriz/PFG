package com.gestion.gestionAlumnos.core.network

import com.gestion.gestionAlumnos.data.model.Alumno
import com.gestion.gestionAlumnos.data.model.Asignatura
import com.gestion.gestionAlumnos.data.model.Calificacion
import com.gestion.gestionAlumnos.data.model.LoginRequest
import com.gestion.gestionAlumnos.data.model.LoginResponse
import com.gestion.gestionAlumnos.data.model.Profesor
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("alumnos")
    suspend fun getAlumnos(
        @Header("Authorization") token: String
    ): Response<List<Alumno>>

    @GET("profesores")
    suspend fun getProfesores(
        @Header("Authorization") token: String
    ): Response<List<Profesor>>

    @GET("asignaturas")
    suspend fun getAsignaturas(
        @Header("Authorization") token: String
    ): Response<List<Asignatura>>

    @GET("calificaciones")
    suspend fun getCalificaciones(
        @Header("Authorization") token: String
    ): Response<List<Calificacion>>
}