import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { Link } from "react-router-dom";
import authHeader from '../auth-header';
import ReactPaginate from 'react-paginate';
import axios from 'axios';


//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

import Error from '../Error/error';

class listComponent extends React.Component {
    constructor(props){
        super(props);
            this.state = {
                ListaLocais:[],
                loading: true,
                offset: 0,
                perPage: 6,
                currentPage: 0,
            }
            this.handlePageClick = this.handlePageClick.bind(this);   
    }

    async componentDidMount(){
        await this.loadLocais(); 
    }

//Carregar os dados da BD
async loadLocais(){
    const url = "xxx/local/list";
    await axios.get(url).then(res => {
    this.state.loading = false;
    if(res.data){
        const data = res.data;
        var slice = data.slice(this.state.offset, this.state.offset + this.state.perPage);
        this.setState({ pageCount: Math.ceil(data.length / this.state.perPage),
                        ListaLocais:slice });        
        
    }else{
        alert("Erro ao carregar os locais!");
    }
    }).catch(error => {
        alert(error)
    });
}

async loadMoreData() {
    const url = "xxx/local/list";
    await axios.get(url).then(res => {        
    if(res.data){        
        const data = res.data;        
        var slice = data.slice(this.state.offset, this.state.offset + this.state.perPage);
            this.setState({
                pageCount: Math.ceil(data.length / this.state.perPage),             
                ListaLocais: slice
            });
    }else{
        alert("Falha ao Carregar os Locais !");
    }}).catch(error => {
        alert(error)});
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

async sendDelete(id){
    const baseUrl = "xxx/local/disable/" + id;
    await axios.post(baseUrl, {
        u_local:id            
    }).then(response =>{
        if (response.data) {
            Swal.fire(
                'Desativado !',
                'O utilizador foi desativado.',
                'success')
                this.loadUtilizador()
        }
    }).catch ( error => {
        alert("Error 325 ")
    })
}

onDelete(id){
    Swal.fire({
        title: 'Desativar Local ?',
        text: 'O utilizador não terá acesso ao Local!',
        type: 'warning',    
        showCancelButton: true,
        confirmButtonText: 'Sim, desativar!',
        cancelButtonText: 'Não, manter.'
    }).then((result) => {
        if (result.value) {
            this.sendDelete(id)
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire(
                'Cancelado!',
                'O local permance na aplicação.',
                'error'
        )
    }
    })
}




render()
{
    if(this.state.loading == true){
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
        Lista de Locais
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
        Lista de Locais
        </h3>
        </div>
    </div>
    <hr></hr>
        <br></br>
        <div class="mb-3">
        <a href="/criarlocal">
        <button className="btn btn-success">Adicionar novos local</button>
        </a>
        </div>
        <br></br>
<div class="table-responsive">
<table className="table table-hover table-striped">
    <thead className="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nome</th>
            <th scope="col">Longitude</th>
            <th scope="col">Latitude</th>
            <th scope="col">Editar Local</th>            
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


loadFillData(){
    return this.state.ListaLocais.map((data, index)=>{
    return(
    <tr key={index}>
    <td>{data.l_idlocal}</td>
    <td>{data.l_nome}</td   >
    <td>{data.l_longitude}</td>    
    <td>{data.l_latitude}</td>    
    <td><Link class="btn btn-outline-info " to={"/editLocal/"+data.l_idlocal} >Editar</Link></td>
    </tr>
    )
    });
    }
    }
export default listComponent;