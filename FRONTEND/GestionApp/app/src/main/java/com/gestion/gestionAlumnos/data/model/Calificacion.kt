package com.gestion.gestionAlumnos.data.model

data class Calificacion(
    val id_calificacion: Int,
    val nota: Double,
    val fecha: String,
    val observacion: String?,
    val id_alumno: Int,
    val alumno_nombre: String,
    val alumno_apellidos: String,
    val id_profesor: Int,
    val profesor_nombre: String,
    val profesor_apellidos: String,
    val id_asignatura: Int,
    val asignatura_nombre: String
)