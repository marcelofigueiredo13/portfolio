var Permissao = require('../model/Permissao');

var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
/*
Permissao.create({
    p_idpermissao: 1,
    p_designacao: 'Criar um report'
});
Permissao.create({
    p_idpermissao: 2,
    p_designacao: 'Criar uma avaliacao'
});
Permissao.create({
    p_idpermissao: 3,
    p_designacao: 'Aceder ao mapa'
});
Permissao.create({
    p_idpermissao: 4,
    p_designacao: 'Ver alertas'
});
Permissao.create({
    p_idpermissao: 5,
    p_designacao: 'Criar alertas'
});
Permissao.create({
    p_idpermissao: 6,
    p_designacao: 'Gerir utilizadores'
});*/

            




const data = Permissao.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await Permissao.findAll();
res.json(data)
}

module.exports = controllers;
