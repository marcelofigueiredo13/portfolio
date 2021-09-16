var sequelize = require('../model/database');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');

const TipoUtilizador = require('../model/TipoUtilizador');
var Utilizador = require('../model/Utilizador');
const Avatar = require('../model/Avatar');
const Pais = require('../model/Pais');
var Avaliacao = require('../model/Avaliacao');
var Report = require('../model/Report');
var Local = require('../model/Local');

const controllers = {}
sequelize.sync()

controllers.getutilizador = async (req,res) => {
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

controllers.profile = async (req,res) => {
    const { id, nome, pw, email, telefone} = req.params;
    sequelize.query("call atualiza_utilizador("+ id + ",'" + nome + "','" + email + "'," + telefone + ",'" + pw + "');")
    .then(data => {
      res.status(200)
        .json(data);
    })
    .catch(error => {
        console.log(error);
        next();
    })
}

controllers.password = async (req,res) => {
    const {mail} = req.params;
    sequelize.query("select * from getpassword('"+ mail + "');")
    .then(data => {
      res.status(200)
        .json(data);
    })
    .catch(error => {
        console.log(error);
        next();
    })
}

controllers.ranking = async (req, res) => {
  sequelize.query("select * from ranking;")
  .then(data => {
    res.status(200)
      .json(data);
  })
  .catch(error => {
      console.log(error);
      next();
  })
}

controllers.report = async (req,res) => {
    const { utilizador, nivel, latitude, longitude, local} = req.params;
    sequelize.query("call inserir_report("+ utilizador + "," + nivel + "," + latitude + "," + longitude + ",'" + local + "');")
    .then(data => {
      res.status(200)
        .json(data);
    })
    .catch(error => {
        console.log(error);
        next();
    })
}

controllers.getid = async (req,res) => {
        const { email } = req.params;
        const data = await Utilizador.findAll({
        where: { u_email: email},
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

controllers.register = async (req,res) => {
    const { nome, email, telefone, password} = req.params;
    sequelize.query("select inserir_utilizador('"+ nome + "','" + email + "'," + telefone + ",'" + password + "');")
    .then(data => {
      res.status(200)
        .json(data);
    })
    .catch(error => {
        console.log(error);
        next();
    })
}

controllers.avaliar = async (req,res) => {
    const { utilizador, comentario, ulike, report} = req.params;
    sequelize.query("call inserir_avaliacao("+ utilizador + ",'" + comentario + "','" + ulike + "'," + report + ");")
    .then(data => {
      res.status(200)
        .json(data);
    })
    .catch(error => {
        console.log(error);
        next();
    })
}

controllers.getByUser = async (req,res) => {
    const { idutilizador } = req.params;
    const data = await Avaliacao.findAll({
    where: { u_idutilizador: idutilizador }
    })
    .then(function(data){
    return data;
    })
    .catch(error =>{
    return error;
    })
    res.json(data);
}

controllers.getByReport = async (req,res) => {
    const { idreport } = req.params;
    const data = await Avaliacao.findAll({
    where: { r_idreport : idreport }
    })
    .then(function(data){
    return data;
    })
    .catch(error =>{
    return error;
    })
    res.json(data);
}

controllers.getLikes = async (req,res) => {
    const { reportid } = req.params;
    sequelize.query("select devolvelikes("+ reportid + ");")
    .then(data => {
      res.status(200)
        .json(data);
    })
    .catch(error => {
        console.log(error);
        next();
    })
}

controllers.getReportsByUser = async (req,res) => {
        const { id } = req.params;
        const data = await Report.findAll({
        where: { u_idutilizador: id },
        include: [ Local ]
      })
        .then(function(data){
        return data;
        })
        .catch(error =>{
        return error;
        })
        res.json(data);
}

controllers.get = async (req,res) => {
    const { id } = req.params;
    const data = await Report.findAll({
    where: { r_idreport: id },
    })
    .then(function(data){
    return data;
    })
    .catch(error =>{
    return error;
    })
    res.json(data);
}

controllers.listreports = async ( req, res) => {
const data = await Report.findAll({include: [ Local ]});
res.json(data)
}

module.exports = controllers;
