const express = require("express");
const router = express.Router();
const alumnoController = require("../controllers/alumnoController");
const authMiddleware = require("../middleware/authMiddleware");

router.get("/", authMiddleware, alumnoCnotroller.getAlumno);
router.get("/:id", authMiddleware, alumnoController.getAlumonById);

module.exports = router;