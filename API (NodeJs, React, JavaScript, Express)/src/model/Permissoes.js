var Sequelize = require('sequelize');
var sequelize = require('./database');

// importa o modelo – chave forasteira
var Permissao = require('./Permissao');
var TipoUtilizador = require('./TipoUtilizador');


var Permissoes = sequelize.define('permissoes', {

p_idpermissao: {
    primaryKey: true,
    type: Sequelize.INTEGER,
    references: {
    model: Permissao,
    key: 'p_idpermissao',
    }      
},
tu_idtipo: {
    primaryKey: true,
    type: Sequelize.INTEGER,
    references: {
    model: TipoUtilizador,
    key: 'tu_idtipo',
    }      
}

},

{
timestamps: false,
});
Permissoes.belongsTo(Permissao, { foreignKey: 'p_idpermissao' });
Permissoes.belongsTo(TipoUtilizador, { foreignKey: 'tu_idtipo' });

module.exports = Permissoes