var TipoAlerta = require('../model/TipoAlerta');
var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

//Registar
controllers.create = async (req,res) => {
    // data
    const { ta_idtipo, ta_designacao} = req.body;
    // create
    const data = await TipoAlerta.create({
        ta_designacao: ta_designacao,
        ta_idtipo: ta_idtipo
    })
    .then(function(data){
    return data;
    })
    .catch(error =>{
    console.log("Erro: "+error)
    return error;
    })
    // return res
    res.status(200).json({
    success: true,
    message:"Registado",
    data: data
    });
}

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
//feito
/*
TipoAlerta.create({
ta_idtipo: 1,
ta_designacao: 'zona super movimentada'
});
TipoAlerta.create({
ta_idtipo:2,
ta_designacao: 'desinfeção urgente'
});*/

const data = TipoAlerta.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}


controllers.list = async ( req, res) => {
const data = await TipoAlerta.findAll();
res.json(data)
}

module.exports = controllers;
