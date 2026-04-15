package com.gestion.gestionAlumnos.data.model

data class Alumno(
    val id_alumno: Int,
    val nombre: String,
    val apellidos: String,
    val dni: String,
    val fecha_nacimiento: String
)