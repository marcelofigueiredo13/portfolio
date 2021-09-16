var Sequelize = require('sequelize');
var sequelize = require('./database');


var Permissao = sequelize.define('permissao', {

p_idpermissao: {
type: Sequelize.INTEGER,
primaryKey: true
},
p_designacao: Sequelize.STRING
},

{
timestamps: false,
});

module.exports = Permissao
