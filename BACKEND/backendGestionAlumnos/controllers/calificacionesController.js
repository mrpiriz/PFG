const db = require("../config/db");

exports.getCalificaciones = (req, res) => {
    const sql = `
        SELECT
            c.id_calificacion,
            c.nota,
            c.fecha,
            c.observacion,
            a.id_alumno,
            a.nombre AS alumno_nombre,
            a.apellidos AS alumno_apellidos,
            p.id_profesor,
            p.nombre AS profesor_nombre,
            p.apellidos AS profesor_apellidos,
            s.id_asignatura,
            s.nombre AS asignatura_nombre
        FROM calificacion c
        INNER JOIN alumno a ON c.id_alumno = a.id_alumno
        INNER JOIN profesor p ON c.id_profesor = p.id_profesor
        INNER JOIN asignatura s ON c.id_asignatura = s.id_asignatura
        ORDER BY c.fecha DESC
    `;

    db.query(sql, (err, results) => {
        if (err) {
            return res.status(500).json({ message: "Error al obtener calificaciones", error: err });
        }
        res.json(results);
    });
};

exports.getCalificacionById = (req, res) => {
    const { id } = req.params;

    const sql = `
        SELECT
            c.id_calificacion,
            c.nota,
            c.fecha,
            c.observacion,
            a.id_alumno,
            a.nombre AS alumno_nombre,
            a.apellidos AS alumno_apellidos,
            p.id_profesor,
            p.nombre AS profesor_nombre,
            p.apellidos AS profesor_apellidos,
            s.id_asignatura,
            s.nombre AS asignatura_nombre
        FROM calificacion c
        INNER JOIN alumno a ON c.id_alumno = a.id_alumno
        INNER JOIN profesor p ON c.id_profesor = p.id_profesor
        INNER JOIN asignatura s ON c.id_asignatura = s.id_asignatura
        WHERE c.id_calificacion = ?
    `;

    db.query(sql, [id], (err, results) => {
        if (err) {
            return res.status(500).json({ message: "Error al obtener calificación", error: err });
        }

        if (results.length === 0) {
            return res.status(404).json({ message: "Calificación no encontrada" });
        }

        res.json(results[0]);
    });
};