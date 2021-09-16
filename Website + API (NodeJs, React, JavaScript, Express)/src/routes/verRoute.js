const express = require('express');
const router = express.Router();
//importer os controladores [2]
const verController = require('../controllers/verController')
router.get('/testdata', verController.testdata );
router.get('/list', verController.list );


router.get('/save', (req, res) => {
res.json({status: 'Ver Saved'});
});

module.exports = router;