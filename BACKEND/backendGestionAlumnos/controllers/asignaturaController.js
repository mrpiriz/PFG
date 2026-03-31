

const db = require("../config/db");

exports.getAsignaturas = (req, res) => {
    const sql = "SELECT * FROM asignatura ORDER BY nombre";

    db.query(sql, (err, results) => {
        if(err) {
            return res.status(500).json({message: "Error al obtener profesores", error:err});
        }
        res.json(results);
    })
}

exports.getAsignaturaById = (req, res) => {
    const { id } = req.params;

    const sql = "SELECT * FROM asignatura WHERE id_asignatura = ?";

    db.query(sql, [id], (err, results) => {
        if (err) {
            return res.status(500).json({ message: "Error al obtener asignatura", error: err})
        }

        if (results.length === 0) {
            return res.status(404).json({ message: "Asignatura no encontrada"})

        }

        res.json(results[0]);
    })
}