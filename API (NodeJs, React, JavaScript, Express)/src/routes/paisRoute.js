const express = require('express');
const router = express.Router();
//Testar tipo de utilizador
const paisController = require('../controllers/paisController')
router.get('/testdata', paisController.testdata);
router.get('/list', paisController.list)

module.exports = router;
