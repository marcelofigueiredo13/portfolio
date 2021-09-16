var Nivel = require('../model/Nivel');

var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {

/*
Nivel.create({
c_idcor:1,
n_designacao: 'Pouco Populado'
});
Nivel.create({
 c_idcor:2,
n_designacao: 'Muito Populado'
});
Nivel.create({
c_idcor:3,
n_designacao: 'Extremamente Populado'
});*/


const data = Nivel.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await Nivel.findAll();
res.json(data)
}

module.exports = controllers;
