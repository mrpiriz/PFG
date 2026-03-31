const express = require("express");
const router = express.Router();
const profesorController = require("../controllers/profesorController");
const authMiddleware = require("../middleware/authMiddleware");

router.get("/", authMiddleware, profesorController.getProfesores);
router.get("/:id", authMiddleware, profesorController.getProfesorById);

module.exports = router;


