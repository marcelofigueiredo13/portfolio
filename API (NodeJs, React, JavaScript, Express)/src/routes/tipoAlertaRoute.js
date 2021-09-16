const express = require('express');
const router = express.Router();
//Testar tipo de utilizador
const tipoAlertaController = require('../controllers/tipoAlertaController')
router.get('/testdata', tipoAlertaController.testdata);
router.get('/list', tipoAlertaController.list);
router.post('/create', tipoAlertaController.create);

module.exports = router;
