var Sequelize = require('sequelize');
var sequelize = require('./database');

// importa o modelo – chave forasteira
var Cor = require('./Cor');

var Nivel = sequelize.define('nivel', {

n_idnivel: {
type: Sequelize.INTEGER,
primaryKey: true,
autoIncrement: true,
},
n_designacao: Sequelize.STRING,

// chave estrangeira - COR
c_idcor: {
type: Sequelize.INTEGER,
references: {
model: Cor,
key: 'c_idcor'
}
}
},
{
timestamps: false,
});
Nivel.belongsTo(Cor, { foreignKey: 'c_idcor' })

module.exports = Nivel
