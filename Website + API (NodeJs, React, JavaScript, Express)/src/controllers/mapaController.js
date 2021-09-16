var Mapa = require('../model/Mapa');
var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

/*Eliminar uma determinada informação*/
controllers.delete = async (req, res) => {
    // parâmetros por post
    const { m_idmapa } = req.body;
    // delete por sequelize
    const del = await Mapa.destroy({
    where: { m_idmapa: m_idmapa }
    })
    res.json({success:true,deleted:del,message:"Deleted successful"});
}

/* Buscar dados para editar */
controllers.get = async (req,res) => {
    const { id } = req.params;
    const data = await Mapa.findAll({
    where: { m_idmapa: id }
    })
    .then(function(data){
    return data;
    })
    .catch(error =>{
    return error;
    })
    res.json({ success: true, data: data });
    }

    /*Atualizar novos dados*/
    controllers.update = async (req,res) => {
    // parametro get id
    const { id } = req.params;
    // parameter POST
    const {  m_nome } = req.body;
    // Update data
    const data = await Mapa.update({
        m_nome: m_nome    
    },
    {
    where: { m_idmapa: id}
        }).then( function(data){
            return data;
        }).catch(error => {
            return error;
        })
    res.json({success:true, data:data, message:"Updated successful"});
}


/* REGISTAR ---------------------- */
controllers.create = async (req,res) => {
 // data
 const { m_nome } = req.body;
 // create
 const data = await Mapa.create({
 m_nome: m_nome
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
/*
Mapa.create({
m_nome: 'mundo'
});*/


const data = Mapa.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}

controllers.list= async (req, res) => {
const data = await Mapa.findAll({})
.then(function(data){
return data;
})
.catch(error => {
return error;
});
res.json({success : true, data : data});
}
module.exports= controllers;

