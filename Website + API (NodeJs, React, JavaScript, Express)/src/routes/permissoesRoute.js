const express = require('express');
const router = express.Router();


const permissoesController = require('../controllers/permissoesController')
router.get('/testdata', permissoesController.testdata);
router.get('/list', permissoesController.list)

module.exports = router;