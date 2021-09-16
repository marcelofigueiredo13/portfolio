var Avaliacao = require('../model/Avaliacao');

var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {


Avaliacao.create({
av_comentario: 'comentário 1',
av_like: true,
av_data: '2021-09-10',
av_hora: '12:10:10',
u_idutilizador: 1,
r_idreport: 1
});
Avaliacao.create({
av_comentario: 'comentário 2',
av_like: false,
av_data: '2021-09-10',
av_hora: '12:10:10',
u_idutilizador: 2,
r_idreport: 1
});

const data = Avaliacao.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await Avaliacao.findAll();
res.json(data)
}

module.exports = controllers;
