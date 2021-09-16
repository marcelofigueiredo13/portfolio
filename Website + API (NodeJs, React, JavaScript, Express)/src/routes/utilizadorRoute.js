const express = require('express');
const router = express.Router();
const middleware = require('../middleware');
//Testar Utilizador
const utilizadorController = require('../controllers/utilizadorController')

    router.get('/testdata', utilizadorController.testdata);
    router.get('/list', utilizadorController.list)
    router.get('/get/:id', utilizadorController.get);
    router.post('/update/:id', utilizadorController.update);
    router.post('/delete/:id', utilizadorController.delete);
    router.post('/ativate/:id', utilizadorController.ativate);
    router.post('/register',utilizadorController.register);
    router.post('/login',utilizadorController.login);
    router.post('/eliminar', utilizadorController.deleteCA);

module.exports = router;
