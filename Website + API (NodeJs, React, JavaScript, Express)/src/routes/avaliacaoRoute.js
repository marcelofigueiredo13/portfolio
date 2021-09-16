const express = require('express');
const router = express.Router();
//Testar Utilizador
const avaliacaoController = require('../controllers/avaliacaoController')
router.get('/testdata', avaliacaoController.testdata);
router.get('/list', avaliacaoController.list)

module.exports = router;
