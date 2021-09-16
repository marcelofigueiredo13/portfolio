var Alerta = require('../model/Alerta');
var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()
const Report = require('../model/Report');
const TipoAlerta = require('../model/TipoAlerta');

//Listar todos os Alertas
controllers.list= async (req, res) => {
    const data = await Alerta.findAll({
    include: [ Report, TipoAlerta ]
    }).then(function(data){
        return data;
    }).catch(error => {
        return error;
    });
    res.json({success : true, data : data});
}


controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
const data = Alerta.findAll()
return data;
}).catch(err => {
return err;
});
res.json(response)
}

//Atualizar um alerta
controllers.update = async (req,res) => {
    const { id } = req.params;
    const {  ta_idtipo } = req.body;
    const data = await Alerta.update({
        ta_idtipo: ta_idtipo
    },
    {
    where: { al_idalerta: id}
        }).then( function(data){
            return data;
        }).catch(error => {
            return error;
        })
    res.json({success:true, data:data, message:"Alerta atualizado"});
}


module.exports = controllers;
