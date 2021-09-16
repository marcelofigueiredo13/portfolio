import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';

//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

import Error from '../Error/error';

const baseUrl = "xxx";
class EditComponent extends React.Component{
    constructor(props){
    super(props);
        this.state = {
            dataUtilizador:{},
                campEmail: "",
                campNome: "",
                campDataNasc: "",
                campLikes: "",
                campPais: "",
                campPaisNome: "",
                loading: true                
            }
    }

    //Buscar os dados à Base de Dados
    async componentDidMount(){
           await this.MostrarDados();
    }

    async MostrarDados(){
        let userId = this.props.match.params.u_idutilizador;
        console.log(userId);
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
            alert("Mostrar Dados")
        }}).catch(error=>{
            alert("Error server: "+error)
        })  
    }

    //atualizar dados
  async  sendUpdate(){
        let userId = this.props.match.params.u_idutilizador;
        const url = baseUrl+"/utilizador/update/"+ userId
        const datapost = {
                u_email : this.state.campEmail,
                u_nome : this.state.campNome,
                u_datanascimento : this.state.campDataNasc,
                u_likes : this.state.campLikes,
                p_idpais : this.state.campPais
        }
       await axios.post(url,datapost).then(response=>{
                if (response.data) {
                    Swal.fire(
                        'Atualizado',
                    )
                    
                }else {
                    console.log("Falhou");
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
        /*<div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputPassword4">País</label>
                    <select class="form-control">
                        <option value={this.state.campPais} selected>{this.state.campPaisNome}</option>
                        <option onChange={(value)=> this.setState({campPais:value.target.value})}>1</option>                        
                        <option onChange={(value)=> this.setState({campPais:value.target.value})}>2</option>
                        <option onChange={(value)=> this.setState({campPais:value.target.value})}>3</option>
                    </select>                    
                </div>
            </div>*/
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
                  Editar Utilizador
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
            {this.state.campNome}
        </h3>
        </div>
    </div>
    <hr></hr>
        <br></br>

        <div>
        <div class="form-row justify-content-center">
        <div class="form-group col-md-6">
                <label for="inputPassword4">Nome</label>
                <input type="text" class="form-control" placeholder="nome" value={this.state.campNome} onChange={(value)=> this.setState({campNome:value.target.value})}/>
                <br></br>
                <label for="inputPassword4">Email</label>
                <input type="email" class="form-control" placeholder="email" value={this.state.campEmail} onChange={(value)=> this.setState({campEmail:value.target.value})}/>
        </div>                
        <div class="form-group col-md-6">        
                <label for="inputPassword4">Data Nascimento</label>
                <input type="date" class="form-control" placeholder="data"  value={this.state.campDataNasc} onChange={(value)=> this.setState({campDataNasc:value.target.value})}/>
                <br></br>
                <label for="inputPassword4">Likes</label>
                <input type="number" max="50" min="0" class="form-control" placeholder="likes"  value={this.state.campLikes} onChange={(value)=> this.setState({campLikes:value.target.value})}/>
            </div>
        </div>            

        

        <div class="mt-4">
        <button id="botao" type="submit" class="btn btn-primary" onClick={()=>this.onUpdate()}>Atualizar</button>
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
export default EditComponent;