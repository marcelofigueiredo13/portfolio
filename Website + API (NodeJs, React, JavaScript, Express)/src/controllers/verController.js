var Ver = require('../model/Ver');

var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
/* APAGAR após a primeira EXECUÇÃO*/
/*
Ver.create({
u_idutilizador:5,
m_idmapa:1
    });
*/


const data = Ver.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await Ver.findAll();
res.json(data)
}

module.exports = controllers;