const express = require('express');
const router = express.Router();
//importer os controladores [2]
const localController = require('../controllers/localController')
router.get('/testdata', localController.testdata );
router.get('/list', localController.list );
router.post('/create', localController.create);
router.get('/get/:id', localController.get);
router.post('/update/:id', localController.update);

module.exports = router;
