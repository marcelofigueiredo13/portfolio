var Avatar = require('../model/Avatar');
var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
//Feito
/*
Avatar.create({
a_idavatar: 1,
a_avatar: 'url imagem 1',
a_pontos: 25
});
Avatar.create({
a_idavatar: 2,
a_avatar: 'url imagem 2',
a_pontos: 50
});
Avatar.create({
a_idavatar: 3,
a_avatar: 'url imagem 3',
a_pontos: 100
});*/

const data = Avatar.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await Avatar.findAll();
res.json(data)
}

module.exports = controllers;
