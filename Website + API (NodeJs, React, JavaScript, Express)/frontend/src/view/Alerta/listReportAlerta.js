import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import './listReportAlerta.css';
//import { Link } from "react-router-dom";
import axios from 'axios';
import ReactPaginate from 'react-paginate';

//sweetalert2 - importação
//import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'
import Error from '../Error/error';

class listComponent extends React.Component {
    constructor(props){
        super(props);
            this.state = {
                ListaReport:[],
                offset: 0,
                perPage: 6,
                currentPage: 0,
                loading: true
            }
            this.handlePageClick = this.handlePageClick.bind(this);
    }




async componentDidMount(){
    await this.loadReport();
}




handlePageClick = (e) => {
    const selectedPage = e.selected;
    const offset = selectedPage * this.state.perPage;

    this.setState({
        currentPage: selectedPage,
        offset: offset
    }, () => {
        this.loadMoreData()
    });

};


async loadReport(){
    const url = "xxx/report/list";
    await axios.get(url).then(res => {
    if(res.data){
        this.state.loading = false
        const data = res.data;
        var slice = data.slice(this.state.offset, this.state.offset + this.state.perPage);
        this.setState({ 
            pageCount: Math.ceil(data.length / this.state.perPage),
            ListaReport:slice 
        });        
    }else{
        alert("Error ao carregar Report's !");
    }
    }).catch(error => {
        alert(error)
    });
}


handlePageClick = (e) => {
    const selectedPage = e.selected;
    const offset = selectedPage * this.state.perPage;

    this.setState({
        currentPage: selectedPage,
        offset: offset
    }, () => {
        this.loadMoreData()
    });

};

async loadMoreData() {
    const url = "xxx/report/list";
    await axios.get(url).then(res => {        
    if(res.data){        
        const data = res.data;        
        var slice = data.slice(this.state.offset, this.state.offset + this.state.perPage);
            this.setState({
                pageCount: Math.ceil(data.length / this.state.perPage),             
                ListaReport: slice
            });
    }else{
        alert("Falha ao Carregar Report's !");
    }}).catch(error => {
        alert(error)});
}



render()
{
    if(this.state.loading === true){
        return(
            <div class="container">
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
            Situações Reportadas
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
    <div class="container">
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
            Situações Reportadas
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
            <th scope="col">Nivel</th>
            <th scope="col">Utilizador</th>
            <th scope="col">Local</th>
            <th scope="col"></th> 
        </tr>
    </thead>
<tbody>
{this.loadFillData()}
</tbody>
</table>
</div>
<div class="row">
                <ReactPaginate
                    previousLabel={"anterior"}
                    nextLabel={"próxima"}
                    breakLabel={"..."}
                    breakClassName={"break-me"}
                    pageCount={this.state.pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}
                    onPageChange={this.handlePageClick}
                    containerClassName={"pagination"}
                    subContainerClassName={"pages pagination"}
                    activeClassName={"active"}
                />
                </div>
</div>
);
    }else{
        return(
            <Error/>
            );  
    }
}
/*
sendDelete(id){
        // url do backend
        const baseUrl = "http://localhost:3000/utilizador/delete"
        // network
        axios.post(baseUrl,{
            u_idutilizador:id
        }).then(response =>{
            if (response.data.success) {
                Swal.fire(
                    'Eliminado!',
                    'O utilizador foi elimiado.',
                    'success')
                    this.loadReport()
            }
        }).catch ( error => {
            alert("Error 325 ")
        })
}

onDelete(id){
    Swal.fire({
    title: 'Eliminar Utilizador?',
    text: 'Não será possível recuperar o utilizador!',
    type: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Sim, eliminar!',
    cancelButtonText: 'Não, manter.'
    }).then((result) => {
    if (result.value) {
    this.sendDelete(id)
    } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
            'Cancelado!',
            'O utilizador permance na Aplicação.',
            'error'
        )
    }
    })
    }*/

loadFillData(){
    return this.state.ListaReport.map((data, index)=>{
    return(
    <tr key={index}>
    <th>{data.r_idreport}</th>
    <th>{data.r_data}</th>
    <th>{data.r_hora}</th>
    <td>{data.nivel.n_designacao}</td>
    <td>{data.utilizador.u_nome}</td>    
    <td>{data.local.l_nome}</td>    
    <td>
    </td>
    </tr>
    )
    });
    }
    }
export default listComponent;