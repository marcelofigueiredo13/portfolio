var Sequelize = require('sequelize');
var sequelize = require('./database');
// importa o modelo – chave forasteira
var Nivel = require('./Nivel');
var Utilizador = require('./Utilizador');
var Local = require('./Local');


var Report = sequelize.define('report', {
r_idreport: {
type: Sequelize.INTEGER,
primaryKey: true,
autoIncrement: true,
},
r_data: Sequelize.DATEONLY,
r_hora: Sequelize.TIME,
r_limite:Sequelize.BOOLEAN,
//r_limite: Sequelize.BOOLEAN,
// referência a outro modelo - Nível
n_idnivel: {
      type: Sequelize.INTEGER,
        references: {
        model: Nivel,
        key: 'n_idnivel'
        }
    },
// referência a outro modelo - Utilizador
u_idutilizador: {
  type: Sequelize.INTEGER,
  references: {
    model: Utilizador,
    Key: 'u_idutilizador'
  }
},
// referência a outro modelo - Local
l_idlocal: {
  type: Sequelize.INTEGER,
  references: {
    model: Local,
    Key: 'l_idlocal'
  }
},

},
{
timestamps: false,
});
Report.belongsTo(Nivel, { foreignKey: 'n_idnivel' });
Report.belongsTo(Utilizador, { foreignKey: 'u_idutilizador' });
Report.belongsTo(Local, { foreignKey: 'l_idlocal' });
module.exports = Report
