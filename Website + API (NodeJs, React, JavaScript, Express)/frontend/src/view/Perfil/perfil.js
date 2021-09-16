import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';

//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

import Error from '../Error/error';

import './perfil.css';
const baseUrl = "xxx";
class Perfil extends React.Component{
    constructor(props){
    super(props);
        this.state = {
            dataUtilizador:{},
                campEmail: "",
                campNome: "",
                campDataNasc: "",
                campLikes: "",
                selectPais: 0,
                loading: true               
            }
    }

    //Buscar os dados à Base de Dados
    async componentDidMount(){
            let userId = 1;
            const url = baseUrl+"/utilizador/get/"+ userId
        await axios.get(url).then(res=>{
            if (res.data) {
                this.state.loading = false;                    
                    const data = res.data[0]
                        this.setState({
                                dataMapa:data,
                                campEmail: data.u_email,                                
                                campNome: data.u_nome,
                                campDataNasc: data.u_datanascimento,
                                campLikes: data.u_likes,
                                campPais: data.pai.p_idpais,
                                campPaisNome: data.pai.p_nome
                        })                        
            }else {
                alert("Falha ao carregar os dados do Administrador")
            }}).catch(error=>{
                alert("Error server: "+error)
        })
    }

    //atualizar dados
    sendUpdate(){        
        let userId = 1;        
        const url = baseUrl+"/utilizador/update/"+ userId
        // parametros de datos post
        const datapost = {
                u_email : this.state.campEmail,
                u_nome : this.state.campNome,
                u_datanascimento : this.state.campDataNasc,
                u_likes : this.state.campLikes,
                p_idpais : this.state.campPais
        }
        axios.post(url,datapost).then(response=>{
                if (response.data.success===true) {
                    Swal.fire({
                        title: 'Atualizado',
                        text:'Os dados foram alterados.',
                        type:'info'
                    }).then((result) => {
                        if (result.value) {
                            this.props.history.push("/home");
                            window.location.reload()
                        }     
                    })     
                    }else {
                        Swal.fire(
                            'Falhou',
                            'warning'
                        )
                    }
        });
    }

    onUpdate(id){
        Swal.fire({
        title: 'Atualizar Dados ?',
        type: 'info',
        showCancelButton: true,
        confirmButtonText: 'Sim, atualizar',
        cancelButtonText: 'Não'
        }).then((result) => {
        if (result.value) {
            this.sendUpdate();           
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire(
                'Cancelado!',
                'Os dados do utilizador não foram alterados.',
                'info'
            )            
        }
        })
    }
    
    render(){
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
            Perfil do Administrador
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
        <div className="container">
        <br></br>
        <br></br>
    <div className="row">
        <a href="/">
            <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" className="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
            </svg>   
        </a>
        <div className="col-6 justify-content-center">        
        <h3>
            Perfil do Administrador
        </h3>
        </div>
    </div>
    <hr></hr>
        <br></br>

 
        <div class="container">           
        
<form class="well form-horizontal" action=" " method="post"  id="contact_form">
<fieldset>      
        
<div class="form-row justify-content-center">
        <div class="form-group col-md-6">
          <label class="control-label">Nome</label>  
          <div class="inputGroupContainer">
          <div class="input-group">
          <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
          <input  name="first_name" placeholder="nome" class="form-control"  type="text" value={this.state.campNome} onChange={(value)=> this.setState({campNome:value.target.value})}/>
            </div>
          </div>
        </div>

        <div class="form-group col-md-6">
          <label class="control-label">E-Mail</label>  
            <div class="inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
          <input name="email" placeholder="e-mail" class="form-control" type="text" value={this.state.campEmail} onChange={(value)=> this.setState({campEmail:value.target.value})} type="text"/>
            </div>
          </div>
        </div>                                
</div>


        <div class="form-group"> 
          <label class="control-label">País</label>
            <div class="selectContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
            <select name="state" class="form-control selectpicker" onChange={ (value)=> this.setState({selectPais: value.target.value})}>
              <option select>País</option>
                <option value="1">Portugal</option>              
                <option value="2">Afeganistão</option>
                <option value="3">Tawai</option>
            </select>
          </div>
        </div>
        </div>
        
 
        
        <div class="form-group">
          <label class="control-label">Data Nascimento</label>  
            <div class="inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
          <input name="zip" placeholder="Zip Code" value={this.state.campDataNasc} onChange={(value)=> this.setState({campDataNasc:value.target.value})}  class="form-control"  type="date"/>
            </div>
        </div>
        </div>
                                  
        <div class="form-group">
          <label class="control-label">Likes</label>
            <div class="inputGroupContainer">
            <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
            <input name="zip" placeholder="Zip Code" value={this.state.campLikes} onChange={(value)=> this.setState({campLikes:value.target.value})} class="form-control"  type="number"/>
          </div>
          </div>
        </div>                                
       
        </fieldset>
        </form>
            </div>

            <div class="row justify-content-center">
                <div className="mt-4">
                    <button id="botao" type="submit" className="btn btn-secondary" onClick={()=>this.onUpdate()}>Atualizar</button>
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
export default Perfil;