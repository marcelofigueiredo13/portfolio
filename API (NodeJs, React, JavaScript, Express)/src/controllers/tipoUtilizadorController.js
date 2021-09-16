var TipoUtilizador = require('../model/TipoUtilizador');
var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
//Feito
/*TipoUtilizador.create({
tu_idtipo: 1,
tu_designacao: 'utilizador'
});
TipoUtilizador.create({
tu_idtipo: 2,
tu_designacao: 'Administrador'
});*/

const data = TipoUtilizador.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await TipoUtilizador.findAll();
res.json(data)
}

module.exports = controllers;
