const express = require('express');
const router = express.Router();
//Testar Utilizador
const nivelController = require('../controllers/nivelController')
router.get('/testdata', nivelController.testdata);
router.get('/list', nivelController.list)

module.exports = router;