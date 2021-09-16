const express = require('express');
const router = express.Router();
const middleware = require('../middleware');
//Testar Utilizador
const alertarController = require('../controllers/alertaController')
router.get('/testdata', alertarController.testdata);

router.get('/list'/*, middleware.checkToken*/, alertarController.list)

router.post('/update/:id', alertarController.update);

module.exports = router;
