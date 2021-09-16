import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { Link } from "react-router-dom";


//sweetalert2 - importação

import 'sweetalert2/src/sweetalert2.scss'

import './listUtilizador.css'

import Error from '../Error/error';

class listComponent extends React.Component {
    constructor(props){
        super(props);

}




render()
{
    if (localStorage.getItem('user')!= null) {
return (
    <div id="page-container" class="container">
        <br></br>
        <br></br>
    <div class="row">
        <a href="/">
            <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
            </svg>   
        </a>
        <div class="col-6 justify-content-center">        
        <h3>
          Tipos de Utilizadores
        </h3>
        </div>
    </div>
    <hr></hr>
        <br></br>

        <div class="row">
        <div class="col-lg-4">
        <div class="card card-margin">            
            <div class="card-header no-border">
                <h5 class="card-title">Administradores</h5>
            </div>
            <div class="card-body pt-0">                            
        <div class="widget-49">
            <div class="widget-49-title-wrapper">
                <div class="widget-49-date-primary">
                    <span class="widget-49-date-day">A</span>                    
                </div>
                <div class="widget-49-meeting-info">
                    <span class="widget-49-pro-title">Administradores</span>                    
                </div>
            </div>
            <br></br>
            <div class="widget-49-meeting-action">                
                <Link class="btn btn-outline-info " to={"/listaAdministrador"} >Visualizar</Link>
            </div>
        </div>
        </div>
        </div>
    </div>

    <div class="col-lg-4">
    <div class="card card-margin">            
        <div class="card-header no-border">
            <h5 class="card-title">Utilizadores</h5>
        </div>
        <div class="card-body pt-0">                            
    <div class="widget-49">
        <div class="widget-49-title-wrapper">
            <div class="widget-49-date-primary">
                <span class="widget-49-date-day">U</span>                    
            </div>
            <div class="widget-49-meeting-info">
                <span class="widget-49-pro-title">Utilizadores</span>                    
            </div>
        </div>
        <br></br>
        <div class="widget-49-meeting-action">                
            <Link class="btn btn-outline-info " to={"/listaUtilizador"} >Visualizar</Link>
        </div>
    </div>
    </div>
    </div>
</div>
            
<div class="col-lg-4">
    <div class="card card-margin">            
        <div class="card-header no-border">
            <h5 class="card-title">Candidatos Administradores</h5>
        </div>
        <div class="card-body pt-0">                            
    <div class="widget-49">
        <div class="widget-49-title-wrapper">
            <div class="widget-49-date-primary">
                <span class="widget-49-date-day">CA</span>                    
            </div>
            <div class="widget-49-meeting-info">
                <span class="widget-49-pro-title">Candidatos Administradores</span>                    
            </div>
        </div>
    
        <br></br>
        <div class="widget-49-meeting-action">                
            <Link class="btn btn-outline-info " to={"/listaCandidatos"} >Visualizar</Link>
        </div>
    </div>
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
export default listComponent;