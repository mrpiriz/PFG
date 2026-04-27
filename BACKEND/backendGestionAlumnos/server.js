require("dotenv").config();
const express = require("express");
const cors = require("cors");

const authRoutes = require("./routes/authRoutes");
const alumnoRoutes = require("./routes/alumnoRoutes")
const profesorRoutes = require("./routes/profesorRoutes");
const asignaturaRoutes = require("./routes/asignaturaRoutes");
const calificacionesRoutes = require("./routes/calificacionesRoutes");

const app = express();

app.use(cors());
app.use(express.json());

app.use("/api/auth", authRoutes);
app.use("/api/alumnos", alumnoRoutes);
app.use("/api/profesores", profesorRoutes);
app.use("/api/asignaturas", asignaturaRoutes);
app.use("/api/calificaciones", calificacionesRoutes);

const PORT = process.env.PORT || 3007;

app.listen(PORT, () => {
    console.log(`Servidor corriendo en puerto ${PORT}`);
});


