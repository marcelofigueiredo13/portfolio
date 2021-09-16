const express = require('express');
const router = express.Router();

//importer os controladores [2]
const corController = require('../controllers/corController')
router.get('/testdata', corController.testdata );
router.get('/list', corController.list );


router.get('/save', (req, res) => {
res.json({status: 'Cor Saved'});
});

module.exports = router;
