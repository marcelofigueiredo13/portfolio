var Sequelize = require('sequelize');
var sequelize = require('./database');
// importa o modelo – chave forasteira

var Utilizador = require('./Utilizador');
var Report = require('./Report');

var Avaliacao = sequelize.define('avaliacao', {
av_idavaliacao: {
type: Sequelize.INTEGER,
primaryKey: true,
autoIncrement: true,
},
av_comentario: Sequelize.STRING,
av_like: Sequelize.BOOLEAN,
av_data: Sequelize.DATEONLY,
av_hora: Sequelize.TIME,

//chave estrangeira - UTILIZADOR
u_idutilizador: {
type: Sequelize.INTEGER,
references: {
model: Utilizador,
key: 'u_idutilizador'
}
},
//chave estrangeira - REPORT
r_idreport: {
type: Sequelize.INTEGER,
references: {
model: Report,
key: 'r_idreport'
},
av_estado:{type: Sequelize.BOOLEAN, defaultValue: true}
}

},
{
timestamps: false,
});
Avaliacao.belongsTo(Utilizador,{ foreignKey: 'u_idutilizador' });
Avaliacao.belongsTo(Report, {foreignKey: 'r_idreport'});
module.exports = Avaliacao
