var Report = require('../model/Report');
var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()
const Utilizador = require('../model/Utilizador');
const Local = require('../model/Local');
const Nivel = require('../model/Nivel');

//Listar todos os Reports
controllers.list= async (req, res) => {
    const data = await Report.findAll({
    include: [ Utilizador, Local, Nivel ]
    }).then(function(data){
        return data;
    }).catch(error => {
    return error;
    });
    res.json(data);
}


controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
/*
Report.create({
r_idreport:5,
r_data: '2020-04-26',
r_hora:'10:10',
n_idnivel:2,
u_idutilizador: 5,
l_idlocal: 2
});*/

const data = Report.findAll()
    return data;
}).catch(err => {
    return err;
});
res.json(response)
}

module.exports = controllers;
