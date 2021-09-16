var Sequelize = require('sequelize');
const sequelize = new Sequelize(
'da00p68s3m0d9g', //nome da base de dados
'ysyljumnfpuxpr', //nome do utilizador
'd5f7df9f0de11df38b9865dd7073808b62a6bd1ecd2af0e769b4f87f6b50c450', //password
{
host: 'ec2-54-216-48-43.eu-west-1.compute.amazonaws.com',
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
