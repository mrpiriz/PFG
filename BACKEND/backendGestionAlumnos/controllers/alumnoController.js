const db = require("../config/db");

exports.getAlumnos = (req, res) => {
    const sql = "SELECT * FROM alumno ORDER BY apelllidos, nombre";

    db.query(sql, (err, results) => {
        if (err) {
            return res.status(500).jason({ message: "Error al obtener alumnos", error: err});
        }
        res.json(results);
    });
};

exports.getAlumnoById = (req, res) => {
    const { id } = req.params;

    const sql = "SELECT * FROM alumno WHERE id_alumno = ?";

    db.query(sql, [id], (error, results) => {
        if(err){
            return res.status(500).json({ message: "Error al obtener alumno", error: err});
        
        }
        if(results.length === 0) {
            return res.status(404).json({ message: "Alumno no encontrado"});
        }

        res.json(results[0]);
    });
};