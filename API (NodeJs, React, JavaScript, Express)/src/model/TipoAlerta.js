var Sequelize = require('sequelize');
var sequelize = require('./database');

var TipoAlerta = sequelize.define('tipoalerta', {
ta_idtipo: {
type: Sequelize.INTEGER,
primaryKey: true
},
ta_designacao: Sequelize.STRING
},
{
timestamps: false,
});
module.exports = TipoAlerta
