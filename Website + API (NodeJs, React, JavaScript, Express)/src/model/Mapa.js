var Sequelize = require('sequelize');
var sequelize = require('./database');

var Utilizador = require('./Utilizador')

var Mapa = sequelize.define('mapa', {
m_idmapa: {
   type: Sequelize.INTEGER,
   primaryKey: true,
   autoIncrement: true,
},
m_nome: Sequelize.STRING
},
{
timestamps: false,
});

module.exports = Mapa
