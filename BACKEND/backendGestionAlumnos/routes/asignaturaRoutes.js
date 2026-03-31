const express = require("express");
const router = express.Router();
const asignaturaController = require("../controllers/asignaturaController");
const authMiddleware = require("../middleware/authMiddleware");

router.get("/", authMiddleware, asignaturaController.getAsignaturas);
router.get("/:id", authMiddleware, asignaturaController.getAsignaturaById);

module.exports = router;