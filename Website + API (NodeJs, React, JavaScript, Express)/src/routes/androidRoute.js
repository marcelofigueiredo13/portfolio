const express = require('express');
const router = express.Router();
const androidController = require('../controllers/androidController')

router.get('/utilizador/get/:id', androidController.getutilizador);
router.get('/utilizador/profile/:id/:nome/:pw/:email/:telefone', androidController.profile);
router.get('/utilizador/password/:mail', androidController.password);
router.get('/utilizador/ranking', androidController.ranking);
router.get('/utilizador/report/:utilizador/:nivel/:latitude/:longitude/:local', androidController.report);
router.get('/utilizador/getid/:email', androidController.getid);
router.get('/utilizador/register/:nome/:email/:telefone/:password', androidController.register);
// router.get('/utilizador/register/:new_user', androidController.register);
router.get('/utilizador/avaliar/:utilizador/:comentario/:ulike/:report', androidController.avaliar);
router.get('/avaliacao/getU/:idutilizador', androidController.getByUser);
router.get('/avaliacao/getR/:idreport', androidController.getByReport);
router.get('/report/getLikes/:reportid', androidController.getLikes);
router.get('/report/getReportsByUser/:id', androidController.getReportsByUser);
router.get('/report/get/:id', androidController.get);
router.get('/report/list', androidController.listreports);

module.exports = router;
