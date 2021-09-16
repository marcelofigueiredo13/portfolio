var Utilizador = require('../model/Utilizador');
var sequelize = require('../model/database');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const config = require('../config');

const TipoUtilizador = require('../model/TipoUtilizador');
const Avatar = require('../model/Avatar');
const Pais = require('../model/Pais');
const controllers = {}
sequelize.sync()



//--------------------------------------------
//------------  Operações CRUD -------------
//--------------------------------------------

//listar todos os utilizadores
controllers.list= async (req, res) => {
    const data = await Utilizador.findAll({
    include: [ Pais, TipoUtilizador, Avatar ]
    })
    .then(function(data){
    return data;
    })
    .catch(error => {
    return error;
    });
    res.json(data);
}

//"Eliminar" um determinado utilizador
controllers.delete = async (req,res) => {
    const { id } = req.params;
    sequelize.query("update utilizadors set u_estado = false where u_idutilizador = "+ id + ";")
    .then(data => {
      res.json({message:"Eliminado"});
    })
    .catch(error => {
        console.log(error);
        next();
    })
}

//Eliminar um determinado utilizador
controllers.deleteCA = async (req, res) => {
    const { u_idutilizador } = req.body;
    const del = await Utilizador.destroy({
    where: { u_idutilizador: u_idutilizador}
    })
    res.json({success:true,deleted:del,message:"Deleted successful"});
}

controllers.ativate = async (req,res) => {
    const { id } = req.params;
    sequelize.query("update utilizadors set u_estado = true where u_idutilizador = "+ id + ";")
    .then(data => {
      res.json({message:"Eliminado"});
    })
    .catch(error => {
        console.log(error);
        next();
    })
}

//Procurar um determinado utilizador
controllers.get = async (req,res) => {
    const { id } = req.params;
    const data = await Utilizador.findAll({
    where: { u_idutilizador: id },
    include: [ TipoUtilizador, Avatar, Pais ]
    })
    .then(function(data){
    return data;
    })
    .catch(error =>{
    return error;
    })
    res.json(data);
    }

//Atualizar dados do utilizador
controllers.update = async (req,res) => {
    const { id } = req.params;
    const {  u_email, u_nome, u_datanascimento, u_likes } = req.body;
    const data = await Utilizador.update({
        u_email: u_email,
        u_nome: u_nome,
        u_datanascimento: u_datanascimento,
        u_likes: u_likes
    },
    {
    where: { u_idutilizador: id}
        }).then( function(data){
            return data;
        }).catch(error => {
            return error;
        })
    res.json({success:true, data:data, message:"Updated successful"});
}

//--------------------------------------------
//------------  LOG IN & SIGN IN -------------
//--------------------------------------------

//Registar um novo utilizador
controllers.register = async (req,res) => {
    const { u_nome, u_email, u_telefone, u_datanascimento, u_password, p_idpais, tu_idtipo, a_idavatar, u_likes, u_idutilizador} = req.body;
    const data = await Utilizador.create({
        u_nome: u_nome,
        u_email: u_email,
        u_telefone: u_telefone,
        u_datanascimento: u_datanascimento,
        u_password: u_password,
        p_idpais: p_idpais,
        tu_idtipo: tu_idtipo,
        a_idavatar: a_idavatar,
        u_likes: u_likes,
        u_idutilizador: u_idutilizador
    })
    .then(function(data){
    return data;
    })
    .catch(error =>{
    console.log("Erro: "+error);
    return error;
    })
    res.status(200).json(data);
}


//login - FUNCIONA -
controllers.login = async (req,res) => {
    if (req.body.u_email && req.body.u_password) {
    var u_email = req.body.u_email;
    var u_password = req.body.u_password;
    }
    var utilizador = await Utilizador.findOne({where: { u_email: u_email}}).then(function(data){
            return data;
        }).catch(error =>{
                console.log("Erro: "+error);
                return error;
        })
    if (u_password === null || typeof u_password === "undefined") {
            res.status(403).json({
                success: false,
                message: 'Campos em Branco'
            });
    } else {
            if (req.body.u_email && req.body.u_password && utilizador) {
                if(utilizador.tu_idtipo != 2){
                    res.status(403).json({success: false, message: 'Admin Inválido.'});
                }
                var isMatch = bcrypt.compareSync(req.body.u_password, utilizador.u_password);
                console.log(req.body.u_password + " " + utilizador.u_password);
            if (req.body.u_email === utilizador.u_email && isMatch) {
                let token = jwt.sign({u_email: req.body.u_email}, config.jwtSecret, {expiresIn: '2h'});
                res.json({success: true, message: 'Autenticação realizada com sucesso!', token: token});
            } else {
                res.status(403).json({success: false, message: 'Dados de autenticação inválidos.'});
            }
            } else {
                res.status(400).json({success: false, message: 'Erro no processo de autenticação. Tente de novo mais tarde.'});
            }
}}






//--------------------------------------------
//------------  Operações Anexo --------------
//--------------------------------------------


//listar todos os utilizadores
controllers.testdata = async ( req, res) => {
    const response = await sequelize.sync().then(function() {
        const data = Utilizador.findAll()
        return data;
    }).catch(err => {
    return err;
    });
    res.json(response)
    }


module.exports = controllers;
