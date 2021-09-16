import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { Link } from "react-router-dom";
import authHeader from '../auth-header';

import axios from 'axios';


import Error from '../Error/error';

class listComponent extends React.Component {
    constructor(props){
        super(props);
            this.state = {
                ListaAlerta:[],
                ListaReport:[],
                loading: true
            }   
    }

    async componentDidMount(){
        await this.loadAlerta();
        await this.loadReports();   
    }

//Carregar os dados da BD
async loadAlerta(){
    const url = "https:xxx/alerta/list";
    await axios.get(url, {headers: authHeader()}).then(res => {
    if(res.data.success){
        const data = res.data.data;
        this.setState({ ListaAlerta:data });
        this.state.loading = false;
        console.log("Alertas " + this.state.ListaAlerta)
    }else{
        alert("Error Web Service!");
    }
    }).catch(error => {
        alert(error)
    });
}

//Carregar os reports da BD
async loadReports(){
    const url = "https:xxx/report/list";
    await axios.get(url).then(res => {
        if(res.data){
            const data = res.data;
            this.setState({ ListaReport:data });            
        }else{
            alert("Error ao carregar os reports!");
        }}).catch(error => {
            alert(error)
        });
}


loadLocalDesinfecao(){
    return this.state.ListaReport.map((data)=>{            
        if(data.local.l_longitude && data.local.l_latitude){
            this.state.latitude = data.local.l_latitude;
            this.state.longitude = data.local.l_longitude;
            localStorage.setItem("latitude", this.state.latitude);
            localStorage.setItem("longitude", this.state.longitude);
        }
    });
}


render()
{
    if(this.state.loading === true){
        return(
            <div id="page-container" class="container">
        <br></br>
        <br></br>
    <div class="row">        
        <a href="/">
            <svg id="seta" xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
            </svg>   
        </a>
        <div class="col-6 justify-content-center">        
        <h3>
        Histórico de Alertas
        </h3>
        </div>
    </div>
    <hr></hr>
        <br></br>
        <div class="d-flex justify-content-center">                
                    <span id="loading" className="spinner-border spinner-border-sm mt-5"></span>                
            </div>
        </div>
        )
    }
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
            Histórico de Alertas
        </h3>
        </div>
    </div>
    <hr></hr>
        <br></br>        
<div class="table-responsive">
<table className="table table-hover table-striped">
    <thead className="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Data</th>
            <th scope="col">Hora</th>
            <th scope="col">Local</th>
            <th scope="col">Tipo Alerta</th>
            <th scope="col"></th>
        </tr>
    </thead>
<tbody>
{this.loadFillData()}
</tbody>
</table>
</div>
</div>
);
    }else{
        return(
            <Error/>
            );
    }
}


loadFillData(){
    return this.state.ListaAlerta.map((data, index)=>{
    return(
    <tr key={index}>
    <th>{data.al_idalerta}</th>
    <th>{data.al_data}</th>
    <td>{data.al_hora}</td>
    <tr>{this.state.ListaReport.map((data1, index)=>{
        if(data.report.r_idreport === data1.r_idreport)
        return(
            <td>{data1.local.l_nome}</td>
        );
    }) 
    }</tr>
    <td>{data.tipoalertum.ta_designacao}</td>    
    <td>
    <Link class="btn btn-outline-info " to={"/listaReportforAlerta/" + data.report.l_idlocal} >Visualizar Report's</Link>
    </td>
    </tr>
    )
    });
    }
    }
export default listComponent;