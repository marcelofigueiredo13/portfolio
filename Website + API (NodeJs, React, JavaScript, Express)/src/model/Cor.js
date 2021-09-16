var Sequelize = require('sequelize');
var sequelize = require('./database');

var Cor = sequelize.define('cor', {
c_idcor: {
    type: Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true,
},
c_nome: Sequelize.STRING,
c_red: Sequelize.INTEGER,
c_green: Sequelize.INTEGER,
c_blue: Sequelize.INTEGER
},
{
timestamps: false,
});
module.exports = Cor
