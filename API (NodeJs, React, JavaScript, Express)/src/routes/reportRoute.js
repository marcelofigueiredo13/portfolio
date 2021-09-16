const express = require('express');
const router = express.Router();
//Testar Utilizador
const reportController = require('../controllers/reportController')
router.get('/testdata', reportController.testdata);
router.get('/list', reportController.list)

module.exports = router;