var Sequelize = require('sequelize');
var sequelize = require('./database');
// importa o modelo – chave forasteira

var Utilizador = require('./Utilizador');
var Mapa = require('./Mapa');

var Ver = sequelize.define('ver', {
//chave estrangeira - UTILIZADOR
u_idutilizador: {
  primaryKey: true,
  type: Sequelize.INTEGER,
  references: {
  model: Utilizador,
  key: 'u_idutilizador'
  }
},
//chave estrangeira - MAPA
m_idmapa:{
  primaryKey: true,
  type: Sequelize.INTEGER,
  references: {
  model: Mapa,
  key: 'm_idmapa'
  }
}
},

{
timestamps: false,
});
Ver.belongsTo(Utilizador, { foreignKey: 'u_idutilizador' });
Ver.belongsTo(Mapa, { foreignKey: 'm_idmapa' });

module.exports = Ver
