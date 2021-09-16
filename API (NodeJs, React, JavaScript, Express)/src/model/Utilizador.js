var Sequelize = require('sequelize');
var sequelize = require('./database');
const bcrypt = require('bcryptjs'); //encripta a pass a guardar na BD

// importa o modelo – chave forasteira
var Pais = require('./Pais');
var TipoUtilizador = require('./TipoUtilizador');
var Avatar = require('./Avatar');



var Utilizador = sequelize.define('utilizador', {

u_idutilizador: {
    type: Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true,
},
u_nome: Sequelize.STRING,

u_datanascimento: Sequelize.DATEONLY,

u_password: {
    type: Sequelize.STRING,
},

u_email: {
    type: Sequelize.STRING,
    allowNull: false
},
u_telefone: Sequelize.INTEGER,
u_likes: Sequelize.INTEGER,

//chave estrangeira - PAIS
p_idpais: {
      type: Sequelize.INTEGER,
      references: {
      model: Pais,
      key: 'p_idpais',
      }
},
//chave estrangeira - TipoUtilizador
tu_idtipo: {
      type: Sequelize.INTEGER,
      references: {
      model: TipoUtilizador,
      key: 'tu_idtipo',
      }
},
//chave estrangeira - Avatar
a_idavatar: {
      type: Sequelize.INTEGER,
      references: {
      model: Avatar,
      key: 'a_idavatar',
      }
},
u_estado:{type: Sequelize.BOOLEAN, defaultValue: true}
},
{
timestamps: false,
});

//encriptar password do utilizador
Utilizador.beforeCreate((utilizador, options) => {
    return bcrypt.hash(utilizador.u_password, 10)
        .then(hash => {
            utilizador.u_password = hash;
        }).catch(err => {
            throw new Error();
        });
});


Utilizador.belongsTo(Pais, { foreignKey: 'p_idpais' });
Utilizador.belongsTo(TipoUtilizador, { foreignKey: 'tu_idtipo' });
Utilizador.belongsTo(Avatar, { foreignKey: 'a_idavatar' });

module.exports = Utilizador
