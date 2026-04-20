const express = require("express");
const router = express.Router();
const calificacionesController = require("../controllers/calificacionesController");
const authMiddleware = require("../middleware/authMiddleware");

router.get("/", authMiddleware, calificacionesController.getCalificaciones);
router.get("/:id", authMiddleware, calificacionesController.getCalificacionesById);

module.exports = router;