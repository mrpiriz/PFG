package com.gestion.gestionAlumnos.data.model

data class Usuario(
    val id_usuario: Int,
    val email: String,
    val rol: String,
    val activo: Boolean
)