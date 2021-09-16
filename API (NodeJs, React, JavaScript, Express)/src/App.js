const express = require('express');
const app = express();
//Configurações
app.set('port', process.env.PORT || 3000);
//Middlewares
app.use(express.json());
const middleware = require('./middleware');


// Configurar CORS
app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers','Authorization, X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Allow-Request-Method');
    res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, DELETE');
    res.header('Allow', 'GET, POST, OPTIONS, PUT, DELETE');
    next();
});

//Metodos da aplicaçao
const androidRouters = require('./routes/androidRoute.js')
app.use('/android', androidRouters)

//TipoUtilizador -- Funciona
const tipoUtilizadorRouters = require('./routes/tipoUtilizadorRoute.js')
app.use('/tipoutilizador', tipoUtilizadorRouters)

//tipoAlerta -- Funciona
const tipoAlertaRouters = require('./routes/tipoAlertaRoute.js')
app.use('/tipoalerta', tipoAlertaRouters)

//avatar -- Funciona
const avatarRouters = require('./routes/avatarRoute.js')
app.use('/avatar', avatarRouters)

//pais -- Funciona
const paisRouters = require('./routes/paisRoute.js')
app.use('/pais', paisRouters)

//utilizador -- Funciona ***MIDDLEWARE***
const utilizadorRouters = require('./routes/utilizadorRoute.js')
app.use('/utilizador', utilizadorRouters)

//mapa -- Funciona
const mapaRouters = require('./routes/mapaRoute.js')
app.use('/mapa', mapaRouters)

//ver -- Funciona
const verRouters = require('./routes/verRoute.js')
app.use('/ver', verRouters)

//local -- Funciona
const localRouters = require('./routes/localRoute.js')
app.use('/local', localRouters)

//cor -- Funciona
/*const corRouters = require('./routes/corRoute.js')
app.use('/cor', corRouters)*/

//Nivel -- Funciona
/*const nivelRouters = require('./routes/nivelRoute.js')
app.use('/nivel', nivelRouters)*/

//Report -- Funciona
const reportRouters = require('./routes/reportRoute.js')
app.use('/report', reportRouters)

//Avaliacao -- Funciona
/*const reportRouters = require('./routes/avaliacaoRoute.js')
app.use('/avaliacao', reportRouters);*/

//Alerta -- Funciona
const alertaRouters = require('./routes/alertaRoute.js')
app.use('/alerta',alertaRouters/*, middleware.checkToken*/)

//Permissão -- Funciona
/*const permissaoRouters = require('./routes/permissaoRoute.js')
app.use('/permissao', permissaoRouters)*/

//Permissões -- Funciona
/*const permissoesRouters = require('./routes/permissoesRoute.js')
app.use('/permissoes', permissoesRouters)*/



// importação de rotas [1]
/*const employeeRouters = require('./routes/employeeRoute.js')
//Rota
app.use('/employee',employeeRouters)*/

//Rotas

app.use('/teste',(req,res)=>{
res.send("Rota TESTE.");
});
app.use('/',(req,res)=>{
res.send("Hello World");
});
app.listen(app.get('port'),()=>{
console.log("Start server on port "+app.get('port'))
})
