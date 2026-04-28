const db = require("../config/db");

exports.getAlumnos = (req, res) => {
    const sql = "SELECT * FROM alumno ORDER BY apellidos, nombre";

    db.query(sql, (err, results) => {
        if (err) {
            return res.status(500).json({ message: "Error al obtener alumnos", error: err});
        }
        res.json(results);
    });
};

exports.getAlumnoById = (req, res) => {
    const { id } = req.params;

    const sql = "SELECT * FROM alumno WHERE id_alumno = ?";

    db.query(sql, [id], (err, results) => {
        if(err){
            return res.status(500).json({ message: "Error al obtener alumno", error: err});
        
        }
        if(results.length === 0) {
            return res.status(404).json({ message: "Alumno no encontrado"});
        }

        res.json(results[0]);
    });
};

exports.createAlumno = (req, res) => {
    const { id_usuario, nombre, apellidos, dni, fecha_nacimiento } = req.body;

    if (!id_usuario || !nombre || !apellidos || !dni || !fecha_nacimiento) {
        return res.status(400).json({
            message: "id_usuario, nombre, apellidos, dni y fecha_nacimiento son obligatorios"
        });
    }

    const sql = `
        INSERT INTO alumno (id_usuario, nombre, apellidos, dni, fecha_nacimiento)
        VALUES (?, ?, ?, ?, ?)
    `;

    db.query(sql, [id_usuario, nombre, apellidos, dni, fecha_nacimiento], (err, result) => {
        if (err) {
            return res.status(500).json({
                message: "Error al crear alumno",
                error: err
            });
        }

        res.status(201).json({
            message: "Alumno creado correctamente",
            id_alumno: result.insertId
        });
    });
};

exports.updateAlumno = (req, res) => {
    const { id } = req.params;
    const { id_usuario, nombre, apellidos, dni, fecha_nacimiento } = req.body;

    if (!id_usuario || !nombre || !apellidos || !dni || !fecha_nacimiento) {
        return res.status(400).json({
            message: "id_usuario, nombre, apellidos, dni y fecha_nacimiento son obligatorios"
        });
    }

    const sql = `
        UPDATE alumno
        SET id_usuario = ?, nombre = ?, apellidos = ?, dni = ?, fecha_nacimiento = ?
        WHERE id_alumno = ?
    `;

    db.query(sql, [id_usuario, nombre, apellidos, dni, fecha_nacimiento, id], (err, result) => {
        if (err) {
            return res.status(500).json({
                message: "Error al actualizar alumno",
                error: err
            });
        }

        if (result.affectedRows === 0) {
            return res.status(404).json({ message: "Alumno no encontrado" });
        }

        res.json({ message: "Alumno actualizado correctamente" });
    });
};

exports.deleteAlumno = (req, res) => {
    const { id } = req.params;

    const sql = `
        DELETE FROM alumno
        WHERE id_alumno = ?
    `;

    db.query(sql, [id], (err, result) => {
        if (err) {
            return res.status(500).json({
                message: "Error al eliminar alumno",
                error: err
            });
        }

        if (result.affectedRows === 0) {
            return res.status(404).json({ message: "Alumno no encontrado" });
        }

        res.json({ message: "Alumno eliminado correctamente" });
    });
};