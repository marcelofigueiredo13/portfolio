var Sequelize = require('sequelize');
var sequelize = require('./database');
// importa o modelo – chave forasteira
 
var Report = require('./Report');
var TipoAlerta = require('./TipoAlerta');

var Alerta = sequelize.define('alerta', {

al_idalerta: {
type: Sequelize.INTEGER,
primaryKey: true,
autoIncrement: true,
},
al_data: Sequelize.DATEONLY,
al_hora: Sequelize.TIME,

//chave estrangeira - Report
r_idreport: {
type: Sequelize.INTEGER,
references: {
model: Report,
key: 'r_idreport'
}
},
//chave estrangeira - TipoAlerta
ta_idtipo: {
type: Sequelize.INTEGER,
references: {
model: TipoAlerta,
key: 'ta_idtipo'
    }
  }
},

{
timestamps: false,
});
Alerta.belongsTo(Report, { foreignKey: 'r_idreport' });
Alerta.belongsTo(TipoAlerta, {foreignKey: 'ta_idtipo'});
module.exports = Alerta
