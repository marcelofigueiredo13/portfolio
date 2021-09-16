import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { Link } from "react-router-dom";
import axios from 'axios';
import ReactPaginate from 'react-paginate';
//import authHeader from '../auth-header';

//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'
import './listUtilizador.css'


import Error from '../Error/error';


//Exportar Imagens
import Ouro from '../images/avatar_gold.png';
import Prata from '../images/avatar_silver.png';
import Bronze from '../images/avatar_bronze.png';



class listComponent extends React.Component {
    constructor(props){
        super(props);
            this.state = {
                ListaUtlizador:[],
                offset: 1,
                perPage: 6,
                currentPage: 0,
                loading: true
            }
        
            this.handlePageClick = this.handlePageClick.bind(this);
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

 async componentDidMount(){
    await this.loadUtilizador();
 }
    
async loadMoreData() {
    const url = "https://crowdzeroapieuropeappandsite.herokuapp.com/utilizador/list";
    await axios.get(url).then(res => {        
    if(res.data){
        this.state.loading = false;
        const data = res.data;        
        var slice = data.slice(this.state.offset, this.state.offset + this.state.perPage);
            this.setState({
                pageCount: Math.ceil(data.length / this.state.perPage),             
                ListaUtlizador: slice
            });
    }else{
        alert("Falha ao Carregar Utilizadores!");
    }}).catch(error => {
        alert(error)});
}


async loadUtilizador(){
    const url = "https://crowdzeroapieuropeappandsite.herokuapp.com/utilizador/list";
    await axios.get(url).then(res => {        
    if(res.data){
        this.state.loading = false;
        const data = res.data;        
        var slice = data.slice(this.state.offset, this.state.offset + this.state.perPage);
        this.setState({
            pageCount: Math.ceil(data.length / this.state.perPage),             
            ListaUtlizador: slice 
        });
    }else{
        alert("Falha ao Carregar Utilizadores!");
    }
    }).catch(error => {
        alert(error)
    });
}

render()
{
    if(this.state.loading === true){
        return(
            <div class="container">
        <br></br>
        <br></br>
    <div class="row">
        <a href="/tipoutilizador">
            <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
            </svg>   
        </a>
        <div class="col-6 justify-content-center">        
        <h3>
          Lista de Utilizadores
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
        <a href="/tipoutilizador">
            <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
            </svg>   
        </a>
        <div class="col-6 justify-content-center">        
        <h3>
          Lista de Utilizadores
        </h3>
        </div>
    </div>
    <hr></hr>
        <br></br>

        <div class="row">
            {this.loadFillData()}
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

async sendDelete(id){
        const baseUrl = "https://crowdzeroapieuropeappandsite.herokuapp.com/utilizador/delete/" + id;
        await axios.post(baseUrl, {
            u_idutilizador:id            
        }).then(response =>{
            if (response.data) {
                Swal.fire(
                    'Eliminado!',
                    'O utilizador foi elimiado.',
                    'success')
                    this.loadUtilizador()
            }
        }).catch ( error => {
            alert("Error 325 ")
        })
}

onDelete(id){
    Swal.fire({
    title: 'Desativar Utilizador?',
    text: 'O utilizador não terá acesso à aplicação!',
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
    }

    Alerta_ativar(id){
        Swal.fire({
        title: 'Ativar Utilizador?',
        text: 'Utilizador poderá voltar usar aplicação!',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sim, Ativar!',
        cancelButtonText: 'Não.'
        }).then((result) => {
        if (result.value) {
        this.sendAtivate(id)
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire(
                'Cancelado!',
                'O utilizador permance na Aplicação.',
                'error'
            )
        }
        })
        }

        async sendAtivate(id){
            const baseUrl = "https://crowdzeroapieuropeappandsite.herokuapp.com/utilizador/ativate/" + id;
            console.log("Utilizador: " + id);
            await axios.post(baseUrl, {
                u_idutilizador:id            
            }).then(response =>{
                if (response.data) {
                    Swal.fire(
                        'Utilizador Ativo!',
                        'O utilizador ativo no sistema.',
                        'success')
                        this.loadUtilizador()
                }
            }).catch ( error => {
                alert("Error 325 ")
            })
    }




loadFillData(){
    return this.state.ListaUtlizador.map((data)=>{
     if(data.tu_idtipo === 1 && data.a_idavatar === 2 && data.u_estado === true) {
        return(
        <div class="col-lg-4">
        <div class="card card-margin">           
            <div class="card-header no-border">
                <h5 class="card-title">Utilizador</h5>
            </div>
            <div class="card-body pt-0">                            
        <div class="widget-49">
            <div class="widget-49-title-wrapper">
                <div class="widget-49-date-primary">
                    <span class="widget-49-date-day"><img src={Prata}/></span>                    
                </div>
                <div class="widget-49-meeting-info">
                    <span class="widget-49-pro-title">{data.u_nome}</span>                    
                    <span class="widget-49-meeting-time">{data.u_datanascimento}</span>
                </div>
            </div>
            <ol class="widget-49-meeting-points">
                <li class="widget-49-meeting-item"><span>{data.u_email}</span></li>
                <li class="widget-49-meeting-item"><span>{data.pai.p_nome}</span></li>
                <li class="widget-49-meeting-item"><span><svg id="like" xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-hand-thumbs-up-fill mb-1" viewBox="0 0 16 16">
  <path d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
</svg>{data.u_likes}</span></li>
            </ol>
            <br></br>
            <div class="widget-49-meeting-action">
                <button id="eliminar" class="btn btn-outline-danger" onClick={()=>this.onDelete(data.u_idutilizador)}> Desativar </button>
                <Link class="btn btn-outline-info " to={"/editUtilizador/"+data.u_idutilizador} >Editar</Link>
            </div>
        </div>
        </div>
        </div>
    </div>
        )
    }

    if(data.tu_idtipo === 1 && data.a_idavatar === 1 && data.u_estado === true) {
        return(
        <div class="col-lg-4">
        <div class="card card-margin">           
            <div class="card-header no-border">
                <h5 class="card-title">Utilizador</h5>
            </div>
            <div class="card-body pt-0">                            
        <div class="widget-49">
            <div class="widget-49-title-wrapper">
                <div class="widget-49-date-primary">
                    <span class="widget-49-date-day"><img src={Bronze}/></span>                    
                </div>
                <div class="widget-49-meeting-info">
                    <span class="widget-49-pro-title">{data.u_nome}</span>
                    <span class="widget-49-meeting-time">{data.u_datanascimento}</span>
                </div>
            </div>
            <ol class="widget-49-meeting-points">
                <li class="widget-49-meeting-item"><span>{data.u_email}</span></li>
                <li class="widget-49-meeting-item"><span>{data.pai.p_nome}</span></li>
                <li class="widget-49-meeting-item"><span><svg id="like" xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-hand-thumbs-up-fill mb-1" viewBox="0 0 16 16">
  <path d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
</svg>{data.u_likes}</span></li>
            </ol>
            <br></br>
            <div class="widget-49-meeting-action">
                <button id="eliminar" class="btn btn-outline-danger" onClick={()=>this.onDelete(data.u_idutilizador)}> Desativar </button>
                <Link class="btn btn-outline-info " to={"/editUtilizador/"+data.u_idutilizador} >Editar</Link>
            </div>
        </div>
        </div>
        </div>
    </div>
        )
    }

    if(data.tu_idtipo === 1 && data.a_idavatar === 3 && data.u_estado === true) {
        return(
        <div class="col-lg-4">
        <div class="card card-margin">           
            <div class="card-header no-border">
                <h5 class="card-title">Utilizador</h5>
            </div>
            <div class="card-body pt-0">                            
        <div class="widget-49">
            <div class="widget-49-title-wrapper">
                <div class="widget-49-date-primary">
                    <span class="widget-49-date-day"><img src={Ouro}/></span>                    
                </div>
                <div class="widget-49-meeting-info">
                    <span class="widget-49-pro-title">{data.u_nome}</span>
                    <span class="widget-49-meeting-time">{data.u_datanascimento}</span>
                </div>
            </div>
            <ol class="widget-49-meeting-points">
                <li class="widget-49-meeting-item"><span>{data.u_email}</span></li>
                <li class="widget-49-meeting-item"><span>{data.pai.p_nome}</span></li>
                <li class="widget-49-meeting-item"><span><svg id="like" xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-hand-thumbs-up-fill mb-1" viewBox="0 0 16 16">
  <path d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
</svg>{data.u_likes}</span></li>
            </ol>
            <br></br>
            <div class="widget-49-meeting-action">
                <button id="eliminar" class="btn btn-outline-danger" onClick={()=>this.onDelete(data.u_idutilizador)}> Desativar </button>
                <Link class="btn btn-outline-info " to={"/editUtilizador/"+data.u_idutilizador} >Editar</Link>
            </div>
        </div>
        </div>
        </div>
    </div>
        )
    }

    if(data.u_estado === false) {
        return(
            <div class="col-lg-4">
            <div id="disabled" class="card card-margin">           
                <div class="card-header no-border">
                    <h5 class="card-title">Utilizador Desativado</h5>
                </div>
                <div class="card-body pt-0">                            
            <div class="widget-49">
                <div class="widget-49-title-wrapper">
                    <div class="widget-49-date-primary">
                        <span class="widget-49-date-day"><img src={Ouro}/></span>                    
                    </div>
                    <div class="widget-49-meeting-info">
                        <span class="widget-49-pro-title">{data.u_nome}</span>
                        <span class="widget-49-meeting-time">{data.u_datanascimento}</span>
                    </div>
                </div>
                <ol class="widget-49-meeting-points">
                    <li class="widget-49-meeting-item"><span>{data.u_email}</span></li>
                    <li class="widget-49-meeting-item"><span>{data.pai.p_nome}</span></li>
                    <li class="widget-49-meeting-item"><span><svg id="like" xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-hand-thumbs-up-fill mb-1" viewBox="0 0 16 16">
      <path d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
    </svg>{data.u_likes}</span></li>
                </ol>
                <br></br>
                <div class="widget-49-meeting-action">
                    <button id="eliminar" class="btn btn-outline-secondary" onClick={()=>this.Alerta_ativar(data.u_idutilizador)}> Ativar </button>
                    <Link class="btn btn-outline-info " to={"/editUtilizador/"+data.u_idutilizador}>Editar</Link>
                </div>
            </div>
            </div>
            </div>
        </div>
            )
    }

    });
    }
    }


export default listComponent;