package com.gestion.gestionAlumnos.data.model

data class Calificacion(
    val id_calificacion: Int,
    val id_alumno: Int,
    val id_evaluacion: Int,
    val nota: Double,
    val observacion_general: String?
)