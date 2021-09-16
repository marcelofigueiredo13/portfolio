const express = require('express');
const router = express.Router();
//Testar tipo de utilizador
const tipoUtilizadorController = require('../controllers/tipoUtilizadorController')
router.get('/testdata', tipoUtilizadorController.testdata);
router.get('/list', tipoUtilizadorController.list)

module.exports = router;
