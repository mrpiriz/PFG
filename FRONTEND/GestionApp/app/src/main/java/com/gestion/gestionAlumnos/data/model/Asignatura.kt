package com.gestion.gestionAlumnos.data.model

data class Asignatura(
    val id_asignatura: Int,
    val nombre: String,
    val descripcion: String?,
    val horas_semanales: Int
)