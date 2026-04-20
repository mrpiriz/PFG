const express = require("express");
const router = express.Router();
const alumnoController = require("../controllers/alumnoController");
const authMiddleware = require("../middleware/authMiddleware");

router.get("/", authMiddleware, alumnoController.getAlumnos);
router.get("/:id", authMiddleware, alumnoController.getAlumnoById);

module.exports = router;