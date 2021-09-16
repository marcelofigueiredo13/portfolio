var Local = require('../model/Local');
const Mapa = require('../model/Mapa');

var sequelize = require('../model/database');
const controllers = {}
sequelize.sync()

//criar locais
controllers.create = async (req,res) => {
    const { l_idlocal, l_nome, l_longitude, l_latitude } = req.body;
    const data = await Local.create({
            l_nome: l_nome,
            l_longitude: l_longitude,
            l_latitude: l_latitude,
            m_idmapa: 1,
            l_idlocal: l_idlocal
    }).then(function(data){
    return data;
    })
    .catch(error =>{
    console.log("Erro: "+error);
    return error;
    })
    res.status(200).json(data);
}

//Buscar um determinado local
controllers.get = async (req,res) => {
    const { id } = req.params;
    const data = await Local.findAll({
        where: { l_idlocal: id },
        include: [ Mapa ]
    }).then(function(data){
        return data;
    }).catch(error =>{
            return error;
    })

    res.json(data);
}

//editar Local
controllers.update = async (req,res) => {
    const { id } = req.params;
    const {  l_nome, l_longitude, l_latitude} = req.body;
    const data = await Local.update({
        l_nome: l_nome,
        l_longitude: l_longitude,
        l_latitude: l_latitude
    },
    {
    where: { l_idlocal: id}
        }).then( function(data){
            return data;
        }).catch(error => {
            return error;
        })
    res.json({success:true, data:data, message:"local alterado com sucesso"});
}

controllers.testdata = async ( req, res) => {
const response = await sequelize.sync().then(function() {
/*
Local.create({
l_nome: '6th',
l_longitude: '121.0036417',
l_latitude: '14.5806413',
m_idmapa:1
});
Local.create({
l_nome: 'Summerview',
l_longitude: '43.8744857',
l_latitude: '15.5417343',
m_idmapa:1
});
Local.create({
l_nome: 'Hayes',
l_longitude: '-98.2151462',
l_latitude: '19.0561748',
m_idmapa:1
});*/

const data = Local.findAll()
return data;
})
.catch(err => {
return err;
});
res.json(response)
}
controllers.list = async ( req, res) => {
const data = await Local.findAll();
res.json(data)
}

module.exports = controllers;
