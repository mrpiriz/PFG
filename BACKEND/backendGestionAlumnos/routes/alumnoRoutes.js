const express = require("express");
const router = express.Router();
const alumnoController = require("../controllers/alumnoController");
const authMiddleware = require("../middleware/authMiddleware");

router.get("/", authMiddleware, alumnoController.getAlumnos);
router.get("/:id", authMiddleware, alumnoController.getAlumnoById);

router.post("/", authMiddleware, alumnoController.createAlumno);
router.put("/:id", authMiddleware, alumnoController.updateAlumno);
router.delete("/:id", authMiddleware, alumnoController.deleteAlumno);

module.exports = router;