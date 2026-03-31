

const db = require("../config/db");

exports.getProfesores = (req, res) => {
    const sql = "SELECT * FROM profesor ORDER BY apellidos, nombre";

    db.query(sql, (err, results) => {
        if(err) {
            return res.status(500).json({message: "Error al obtener profesores", error:err});
        }
        res.json(results);
    })
}

exports.getProfesorById = (req, res) => {
    const { id } = req.params;

    const sql = "SELECT * FROM profesor WHERE id_profesor = ?";

    db.query(sql, [id], (err, results) => {
        if (err) {
            return res.status(500).json({ message: "Error al obtener profesor", error: err})
        }

        if (results.length === 0) {
            return res.status(404).json({ message: "Profesor no encontrado"})

        }

        res.json(results[0]);
    })
}