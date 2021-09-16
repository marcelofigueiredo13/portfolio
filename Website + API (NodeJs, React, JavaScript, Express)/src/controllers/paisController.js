var Pais = require('../model/Pais');
var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
//Funciona
/*
Pais.create({
p_idpais:1,
p_nome: 'Portugal',
p_codigo: 'PT',
p_indicativo: 351
});
Pais.create({
p_idpais:2,
p_nome: 'Tawai',
p_codigo: 'TW',
p_indicativo: 886
});
Pais.create({
p_idpais:3,
p_nome: 'Afghanistan',
p_codigo: 'AF',
p_indicativo: 93
});*/


const data = Pais.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await Pais.findAll();
res.json(data)
}

module.exports = controllers;
