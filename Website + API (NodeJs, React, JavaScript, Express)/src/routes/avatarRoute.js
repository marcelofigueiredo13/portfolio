const express = require('express');
const router = express.Router();
//Testar tipo de utilizador
const avatarController = require('../controllers/avatarController')
router.get('/testdata', avatarController.testdata);
router.get('/list', avatarController.list)

module.exports = router;
