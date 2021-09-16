var Cor = require('../model/Cor');

var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
/*
Cor.create({
c_nome: 'Verde',
c_red: 0,
c_green: 255,
c_blue: 0
});

Cor.create({
c_nome: 'Laranja',
c_red: 237,
c_green: 101,
c_blue: 17
});

Cor.create({
c_nome: 'Vermelho',
c_red: 255,
c_green: 0,
c_blue: 0
});*/


const data = Cor.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await Cor.findAll();
res.json(data)
}

module.exports = controllers;
