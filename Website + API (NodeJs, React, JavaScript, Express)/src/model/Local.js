var Sequelize = require('sequelize');
var sequelize = require('./database');
// importa o modelo – chave forasteira
var Mapa = require('./Mapa');

var Local = sequelize.define('local', {
l_idlocal: {
type: Sequelize.INTEGER,
primaryKey: true,
autoIncrement: true,
},
l_nome: Sequelize.STRING,
l_longitude: Sequelize.FLOAT,
l_latitude: Sequelize.FLOAT,

//chave estrangeira - MAPA
m_idmapa: {
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
Local.belongsTo(Mapa, {foreignKey: 'm_idmapa'});

/*Local.belongsTo(Mapa,  {
    through: "local",
    as: "mapa",
    foreignKey: "m_idmapa",
})*/
module.exports = Local
