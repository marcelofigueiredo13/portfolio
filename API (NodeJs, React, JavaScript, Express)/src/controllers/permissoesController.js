var Permissoes = require('../model/Permissoes');

var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {

Permissoes.create({
    p_idpermissao: 1,
    tu_idtipo: 1
});
Permissoes.create({
    p_idpermissao: 1,
    tu_idtipo: 2
});
Permissoes.create({
    p_idpermissao: 2,
    tu_idtipo: 1
});
Permissoes.create({
    p_idpermissao: 2,
    tu_idtipo: 2
});
Permissoes.create({
    p_idpermissao: 3,
    tu_idtipo: 1
});
Permissoes.create({
    p_idpermissao: 3,
    tu_idtipo: 2
});
Permissoes.create({
    p_idpermissao: 4,
    tu_idtipo: 2
});
Permissoes.create({
    p_idpermissao: 5,
    tu_idtipo: 2
});
Permissoes.create({
    p_idpermissao: 6,
    tu_idtipo: 2
});
            


const data = Permissoes.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await Permissoes.findAll();
res.json(data)
}

module.exports = controllers;
