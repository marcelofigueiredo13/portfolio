import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";


import Navbar from './view/nav-bar/Navbar';
import MapaMundo from './view/MapaMundo/MapaMundo';

import PageNotFound from './view/404/404';

import Local from './view/Locais/locais';
import EditLocal from './view/Locais/editLocal';
import ListaLocais from './view/Locais/listaLocais';

import ListAlerta from './view/Alerta/listAlerta';
import ListReportAlerta from './view/Alerta/listReportAlerta';
import ListReportforAlerta from './view/Alerta/listReportforAlerta';

import List from './view/Utilizadores/listUtilizador';
import EditUser from './view/Utilizadores/editUtilizador';
import FormTipoAlerta from './view/fomTipoAlerta';
import FormMundo from './view/formMundo';
import ListMapa from './view/listMapa';
import EditMapa from './view/editMapa';

import AuthService from './view/auth.service';
import Login from './view/Login/login';
import CriarConta from './view/Utilizadores/CriarAdmin/criaradmin';

import Perfil from './view/Perfil/perfil';

import Home from './view/Home/Home';

import Error from './view/Error/error';

import DesinfecaoUrgente from './view/Notificacoes/DesinfecaoUrgente/desinfecaourgente';
import ZonaSuperMovimentada from './view/Notificacoes/ZonaSuperMovimentada/zonasupermovimentada';

import Diariamente from './view/Diariamente/diariamente';
import Semanalmente from './view/Semanalmente/semanalmente';

import TipoUtilizador from './view/Utilizadores/tipoUtilizador';
import ListaAdministrador from './view/Utilizadores/listaAdministrador';
import CandidatoAdmin from './view/Utilizadores/candidatosAdmin';

import Footer from './view/footer/footer'

class App extends React.Component{
  constructor(props) {
    super(props);
    this.logOut= this.logOut.bind(this);
    this.state= {currentUser: {u_email: "Anonimo"}};  
  }
  
  logOut() {
    AuthService.logout();
  }
  

 render(){
  const { currentUser} = this.state;
  return (       
    <Router>      
  <Switch>
      <div className="App">
        <Navbar></Navbar>
        <div id="page-container" class="container">          
        <Route path="/" exact component={Home}/> 
        <Route path="/listaAlerta" exact component={ListAlerta} />
        <Route path="/listReportAlerta" component={ListReportAlerta} />
        <Route path="/listaReportforAlerta/:id_local" component={ListReportforAlerta}/>
  
        <Route path="/tipoutilizador" exact component={TipoUtilizador}/>

        <Route path="/listaAdministrador" exact component={ListaAdministrador} />
        <Route path="/listaUtilizador" exact component={List} />
        <Route path="/listaCandidatos" exact component={CandidatoAdmin} />
    
        <Route path="/editUtilizador/:u_idutilizador" exact component={EditUser} />
  
        <Route path="/mapamundo" exact component={MapaMundo} />
        <Route path="/criarlocal" exact component={Local} />
        <Route path="/listalocais" exact component={ListaLocais} />
        <Route path="/editLocal/:l_idlocal" exact component={EditLocal} />

        <Route path="/desinfecaourgente" exact component={DesinfecaoUrgente} />
        <Route path="/zonasupermovimentada" exact component={ZonaSuperMovimentada}/>

        <Route path="/diariamente" exact component={Diariamente} />
        <Route path="/semanalmente" exact component={Semanalmente}/>
        
        <Route path="/formTipoAlerta" component={FormTipoAlerta} />
  
        <Route path="/formMundo" component={FormMundo} />
        <Route path="/listMapa" component={ListMapa} />
        <Route path="/editMapa/:m_idmapa" component={EditMapa} />
        <Route path="/perfil" component={Perfil} />        
        <Route path="/criarconta" component={CriarConta}/>

        <Route exact path="/login" component={Login} />
        <Route path="/error" component={Error}/>
        
                                        
        
  </div>
        <Footer></Footer>
  </div>
  
  </Switch>
  
  </Router>
  );
 }
}
export default App;
