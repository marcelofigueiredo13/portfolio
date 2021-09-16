var Sequelize = require('sequelize');
const sequelize = new Sequelize(
'xxx', //nome da base de dados
'xxx', //nome do utilizador
'xxx', //password
{
host: 'xxx',
port: '5432',
dialect: 'postgres',
ssl:true,
dialectOptions:{
    ssl:{
      require: true,
	rejectUnauthorized: false
    }
}
},

);

module.exports = sequelize;
