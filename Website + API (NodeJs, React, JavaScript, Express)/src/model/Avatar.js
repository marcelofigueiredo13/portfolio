var Sequelize = require('sequelize');
var sequelize = require('./database');

var Avatar = sequelize.define('avatar', {
a_idavatar: {
type: Sequelize.INTEGER,
primaryKey: true,
},
a_avatar: Sequelize.STRING,
a_pontos: Sequelize.INTEGER
},
{
timestamps: false,
});

module.exports = Avatar
