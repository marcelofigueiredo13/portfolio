import React from 'react';
import AuthService from "../auth.service";
import axios from 'axios';
import './navbar.css'
import authHeader from '../auth-header';

import Icon from '../images/icon.png'

const { Component } = require("react")

const baseUrl = "https://crowdzeroapieuropeappandsite.herokuapp.com";
export default class Navbar extends Component{

  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);
    this.state = {
          dataUtilizador:{},        
          ListaAlerta:[],
          ListaLocal:[],
          campNome: "",
          sinoPreto: false,
          sinoBranco: false,
          mostrar: false,
          localUrg: 0,
          localZSM: 0,
          login: true     
      }
  }

  
 logOut() {    
    AuthService.logout();
 }

 async componentDidMount(){    
    await this.LoadAdmin();    
    await this.local();
    await this.loadAlerta();    
 }
 

 //Carregar os Id's dos locais
 async local(){
  const url = "https://crowdzeroapieuropeappandsite.herokuapp.com/local/list";
 await axios.get(url).then(res => {
  if(res.data){
      const data = res.data;
      this.setState({ 
        ListaLocal:data,  
      });
      console.log("Local_ "+ this.state.ListaLocal); 
  }else{
      alert("Falha ao carregar os Locais !");
  }
  }).catch(error => {
      alert(error)
  });    
}
 

//carregar administradores
 async LoadAdmin(){    
    let userId = 1;
    const url = baseUrl+"/utilizador/get/"+ userId
    await axios.get(url).then(res=>{
            if (res.data) {
                    const data = res.data[0]
                        this.setState({
                                dataMapa:data,
                                campNome: data.u_nome
                        })
            }else {
                alert("Falha no nav-bar")
            }
    }).catch(error=>{
                alert("Error server: "+error)
    })
 }

 //Carregar todos os alertas
 async loadAlerta(){
    const url = "https://crowdzeroapieuropeappandsite.herokuapp.com/alerta/list";
   await axios.get(url, {headers: authHeader()}).then(res => {
    if(res.data.success){
        const data = res.data.data;
        this.setState({ 
          ListaAlerta:data,  
        }); 
    }else{
        alert("Falha ao carregar o Alerta !");
    }
    }).catch(error => {
        alert(error)
    });    
  }




/*Notificação Desinfeção Urgente*/

  //Caso exista alerta desinfeção urgente, mostra notificação
 MostrarSinoDesinfecaoUrgente(){
    return this.state.ListaAlerta.map((data)=>{
      if(data.ta_idtipo === 2 && this.state.sinoPreto === false){        
        this.state.sinoPreto = true;
        this.state.localUrg = data.report.l_idlocal;
        this.loadLocalDesinfecao();
        return(
          <div id="sinoDiv" class="col-2">
            <a href="/desinfecaourgente">           
            <svg id="sino" xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-bell-fill" viewBox="0 0 16 16">
              <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
            </svg>
            </a>
          </div>
        );        
      }
    });
  }

  //guardar local de desinfeção
  loadLocalDesinfecao(){
      return this.state.ListaLocal.map((data)=>{
        if(data.l_idlocal === this.state.localUrg){;
            this.state.latitude = data.l_latitude;
            this.state.longitude = data.l_longitude;
            localStorage.setItem("latitude", this.state.latitude);
            localStorage.setItem("longitude", this.state.longitude);
            localStorage.setItem("Local", this.state.localUrg);
        }
      });
    }




/*Notificação de Zona Super Movimentada*/

    //Mostrar Zona Super Movimentada  
  MostrarSinoZonaMovimentada(){
    return this.state.ListaAlerta.map((data)=>{
      if(data.ta_idtipo === 1 && this.state.sinoPreto === false){        
        this.state.sinoPreto = true;
        this.state.localZSM = data.report.l_idlocal;
      this.loadLocalNotificacao();
        return(
          <div id="sinoDiv" class="col-2">
            <a href="/zonasupermovimentada">           
            <svg id="sinoZM" xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-bell-fill" viewBox="0 0 16 16">
              <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
            </svg>
            </a>
          </div>
        );        
      }
    });
  }

  //guardar local de desinfeção
  loadLocalNotificacao(){
    return this.state.ListaLocal.map((data)=>{
      if(data.l_idlocal === this.state.localZSM){;
          this.state.latitude = data.l_latitude;
          this.state.longitude = data.l_longitude;
          localStorage.setItem("latitude", this.state.latitude);
          localStorage.setItem("longitude", this.state.longitude);
          localStorage.setItem("Local", this.state.localZSM);
      }
    });
  }




  
  render(){    
    if (localStorage.getItem('user')!= null) {
      return (
        <body id="page-top">
    <div id="wrapper">
        <div id="content-wrapper" class="d-flex flex-column">         
            <div id="content">             
                <nav class="navbar navbar-expand navbar-light topbar mb-4 static-top shadow">

                  
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                                  

                    <div>
                        <a href="/" class="navbar-brand ml-4">
                        <h5>Crowd-Zero</h5>
                        </a>
                    </div>

                    

                    {this.MostrarSinoDesinfecaoUrgente()}
                    {this.MostrarSinoZonaMovimentada()}
                    <ul class="navbar-nav ml-auto">                                                                                                           
                        <div class="topbar-divider d-none d-sm-block"></div>

               
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">{this.state.campNome}</span>
                                
                            </a>
                 
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="/perfil">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                                    </svg>
                                    </i>
                                    Perfil
                                </a>
                              
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
                                        <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                                    </svg>
                                    </i>
                                    Terminar Sessão
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                




</div>



</div>


</div>



<a class="scroll-to-top rounded" href="#page-top">
<i class="fas fa-angle-up"></i>
</a>

<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="modal-header">
    <h5 class="modal-title" id="exampleModalLabel">Terminar Sessão ?</h5>
    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">×</span>
    </button>
</div>
<div class="modal-footer">
    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
    <a class="btn btn-primary" href="/login" onClick={this.logOut}>Terminar Sessão</a>
</div>
</div>
</div>
</div>



</body>
            );
    }else{
      return (

        <nav class="navbar navbar-expand-lg navbar-light">
          <a class="navbar-brand ml-4">Crowd-Zero</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>                 
        </nav>        
 
      );
    }
    
}
}