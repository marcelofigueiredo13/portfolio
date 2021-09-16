var Sequelize = require('sequelize');
var sequelize = require('./database');

var TipoUtilizador = sequelize.define('tipoutilizador', {
tu_idtipo: {
type: Sequelize.INTEGER,
primaryKey: true,
},
tu_designacao: Sequelize.STRING
},
{
timestamps: false,
});
module.exports = TipoUtilizador
