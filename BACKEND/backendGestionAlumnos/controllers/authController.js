const db = require("../config/db");
const jwt = require("jsonwebtoken");

exports.login = (req, res) => {
    const { email, password } = req.body;

    const sql = "SELECT * FROM usuario WHERE email = ? AND activo = true";

    db.query(sql, [email], (err, results) => {
        if (err) {
            return res.status(500).json({ message: "Error del servidor", error: err});
        
        }
        if (results.length === 0) {
            return res.status(401).json({message: "Usuario no encontrado"});

        }

        const usuario = results[0];

        if (usuario.password_hash !== password) {
            return res.status(401).json({ message: "Contraseña incorrecta"});
        }

        const token = jwt.sign(
            {
                id_usuario: usuario.id_usuario,
                email: usuario.email,
                rol: usuario.rol
            },
            ProcessingInstruction.env.JWT_SECRET,
            { expiresIn: "8h" }
    );

    res.json({
        token,
        usuario: {
            id_usuario: usuario.id_usuario,
            email: usuario.email,
            rol: usuario.rol,
            activo: usuario.activo
        }
    });
    });
};