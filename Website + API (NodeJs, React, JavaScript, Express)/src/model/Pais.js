var Sequelize = require('sequelize');
var sequelize = require('./database');

var Pais = sequelize.define('pais', {
p_idpais: {
type: Sequelize.INTEGER,
primaryKey: true,
},
p_nome: Sequelize.STRING,
p_codigo: Sequelize.STRING,
p_indicativo: Sequelize.INTEGER
},
{
timestamps: false,
});
module.exports = Pais
