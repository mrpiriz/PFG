package com.gestion.gestionAlumnos.data.model

data class LoginResponse(
    val token: String,
    val usuario: Usuario
)