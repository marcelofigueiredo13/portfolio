import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import './Home.css';
import ImagemMundo from '../images/confinamento-no-mundo-740x495.jpg'
import authHeader from '../auth-header';

import axios from 'axios';

import Error from '../Error/error';

import Footer from '../footer/footer';

class home extends React.Component {
    constructor(props){
        super(props);
            this.state = {
                ListaAlerta:[],
                ListaUtlizador:[],
                ListaLocais:[],
                loading: true
            }
    }

async componentDidMount(){
  await this.LoadUtilizadores();  
  await this.loadAlerta();
  await this.loadLocais();              
}


async LoadUtilizadores(){    
    const url = "xxx/utilizador/list";
    await axios.get(url).then(res => {        
    if(res.data){        
        const data = res.data;
        this.setState({ ListaUtlizador:data});                       
    }else{
        alert("Falha ao Carregar Utilizadores!");
    }
    }).catch(error => {
        alert(error)
    });
}

async loadAlerta(){
    const url = "xxx/alerta/list";
    await axios.get(url, {headers: authHeader()}).then(res => {
    if(res.data.success){
        const data = res.data.data;
        this.setState({ ListaAlerta:data });
    }else{
        alert("Falha ao Carregar os Alertas !");
    }
    }).catch(error => {
        alert(error)
    });
}


async loadLocais(){
    const url = "xxx/local/list";
    await axios.get(url).then(res => {        
    this.state.loading = false;    
    if(res.data){
        const data = res.data;
        this.setState({ ListaLocais:data });            
    }else{
        alert("Erro ao carregar os locais!");
    }
    }).catch(error => {
        alert(error)
    });
}


render()
  {
      if(this.state.loading === true){
          return(
            <div class="container-fluid">
                <br></br>
                <br></br>

                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Loading...</h1>
                </div>
                <div class="d-flex justify-content-center">
                <br></br>
                <br></br>           
                    <span id="loading" className="spinner-border spinner-border-sm mt-5"></span>                
                </div> 
                <br></br>
        </div>
          )
      }

    if (localStorage.getItem('user')!= null) {
    return (
        <div id="page-container" class="container-fluid">

        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Menu</h1>
        </div>
       <br></br>
       <br></br>
        
        <div class="row">
        
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="mb-1">
                                <a class="text-xs font-weight-bold text-primary text-uppercase" href="/tipoutilizador"> Utilizadores</a>
                                <svg id="icon" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-people-fill" viewBox="0 0 16 16">
                                        <path d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H7zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                                        <path fill-rule="evenodd" d="M5.216 14A2.238 2.238 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.325 6.325 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1h4.216z"/>
                                        <path d="M4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/>
                                </svg></div>                        
                                <div class="h5 mb-0 font-weight-bold text-gray-800">{this.state.ListaUtlizador.length}</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-calendar fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
        
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-success shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="mb-1"><a class="text-xs font-weight-bold text-success text-uppercase" href="/listaAlerta">Alertas</a>
                                    <svg id="icon" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16">
                                        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                    </svg>
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">{this.state.ListaAlerta.length}</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
        
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-info shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="mb-1"><a class="text-xs font-weight-bold text-info text-uppercase" href="/listalocais">Locais</a>
                                    <svg id="icon" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pin-map-fill" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd" d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8l3-4z"/>
                                            <path fill-rule="evenodd" d="M4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"/>
                                    </svg>
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">{this.state.ListaLocais.length}</div>
                                <div class="row no-gutters align-items-center">
                                    <div class="col-auto">                                
                                    </div>                
                                </div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
        
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-warning shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="mb-1"><a class="text-xs font-weight-bold text-warning text-uppercase" href="/listReportAlerta">Report's</a>
                                <svg id="icon" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-flag-fill" viewBox="0 0 16 16">
                                    <path d="M14.778.085A.5.5 0 0 1 15 .5V8a.5.5 0 0 1-.314.464L14.5 8l.186.464-.003.001-.006.003-.023.009a12.435 12.435 0 0 1-.397.15c-.264.095-.631.223-1.047.35-.816.252-1.879.523-2.71.523-.847 0-1.548-.28-2.158-.525l-.028-.01C7.68 8.71 7.14 8.5 6.5 8.5c-.7 0-1.638.23-2.437.477A19.626 19.626 0 0 0 3 9.342V15.5a.5.5 0 0 1-1 0V.5a.5.5 0 0 1 1 0v.282c.226-.079.496-.17.79-.26C4.606.272 5.67 0 6.5 0c.84 0 1.524.277 2.121.519l.043.018C9.286.788 9.828 1 10.5 1c.7 0 1.638-.23 2.437-.477a19.587 19.587 0 0 0 1.349-.476l.019-.007.004-.002h.001"/>
                                </svg>
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-comments fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
        
        <br></br>
        <br></br>
        <div class="row justify-content-center">
          
            <div class="col-lg-10">
            <div class="card shadow mb-4">
                                        <div class="card-header py-3">
                                            <h5 class="m-0 font-weight-bold text-gray">Mapa do Mundo</h5>
                                        </div>
                                        <div class="card-body">
                                            <div class="text-center">
                                                <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" src={ImagemMundo} alt="..."></img>
                                            </div>
                                            <a id="referencia" rel="nofollow" href="/mapamundo">Visualizar todas as ocorrências ao momento →</a>
                                        </div>
                                    </div>
        
            </div>
        
        </div>
        
        </div>
        
    );
    }else{
        return(
        <Error/>
        );
    }
}

}
export default home;