const express = require('express');
const router = express.Router();
//importer os controladores [2]
const mapaController = require('../controllers/mapaController')
router.get('/testdata', mapaController.testdata );
router.get('/list', mapaController.list );
router.get('/create', mapaController.create );
router.get('/get/:id', mapaController.get);
router.post('/delete', mapaController.delete);
router.post('/update/:id', mapaController.update);


module.exports = router;
