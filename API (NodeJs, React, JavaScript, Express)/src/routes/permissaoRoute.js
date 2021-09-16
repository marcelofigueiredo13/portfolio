const express = require('express');
const router = express.Router();

const permissaoController = require('../controllers/permissaoController')
router.get('/testdata', permissaoController.testdata);
router.get('/list', permissaoController.list)

module.exports = router;