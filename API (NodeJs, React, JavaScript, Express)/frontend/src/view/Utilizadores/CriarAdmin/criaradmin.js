import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import './criaradmin.css'

//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

import Error from '../../Error/error';

import ImagemLogin from '../../images/criar_conta.jpg'



//Verifica se o campo está preenchido
const required = value => {
    if (!value) {
    return (
            <div className="alert alert-danger" role="alert">
                Este campo é de preenchimento obrigatório!
            </div>
    );
    }
};

//Verifica se o tamanho do nome está correto
const requiredTamanho = value =>{
    if(value.length<5){
        return (
            <div className="alert alert-danger" role="alert">
                Faltam caracteres!
            </div>
    );  
    }    
}

const baseUrl = "https://crowdzeroapieuropeappandsite.herokuapp.com";
class Perfil extends React.Component{
    constructor(props){
    super(props);
    this.handleLogin = this.handleLogin.bind(this);
    this.onChangeNome = this.onChangeNome.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.onChangePasswordRepeat = this.onChangePasswordRepeat.bind(this);
        this.state = {
            campEmail: "",
            campPassword: "",
            campPasswordRepeat: "",
            campNome: "",
            campDataNasc: "",                
            selectPais: 0,
            loading: false,
            message: "",
            campTelefone: ""                
        }
    }

    onChangeNome(e) {
        this.setState({campNome:e.target.value});
        this.setState({loading:false});        
    }

    onChangePassword(e) {
        this.setState({campPassword: e.target.value});        
        this.setState({loading:false});
    }

    onChangePasswordRepeat(e) {
        this.setState({campPasswordRepeat: e.target.value});        
        this.setState({loading:false});
    }

  async handleLogin(e) {
        e.preventDefault();
        this.setState({message: "", loading: true});
        this.form.validateAll();
        if (this.state.campPassword != this.state.campPasswordRepeat) {
            this.setState({
                loading: false,
                message: "Password's não são iguais"
            });
            return;
        }
        if (this.checkBtn.context._errors.length === 0) {        
           await this.CriarConta();
            //this.props.history.push("/Home");
            //window.location.reload();
        }else{    
            Swal.fire(
                'Cancelado!',
                'Erro ao criar conta.',
                'error'
            );                
        }                    
    }

    validate(){
       
        if (this.state.campPassword != this.state.campPasswordRepeat) {
            this.setState({
                loading: false,
                message: "Tente Novamente"
            });
          }
    }



    async CriarConta(){              
        const baseUrl = "https://crowdzeroapieuropeappandsite.herokuapp.com/utilizador/register"
        const datapost = {
            u_nome: this.state.campNome,    
            u_email: this.state.campEmail,
            u_datanascimento: this.state.campDataNasc,
            u_password: this.state.campPassword,
            p_idpais : this.state.selectPais,
            tu_idtipo: 3,
            u_likes: 0,
            a_idavatar: 1,
            u_telefone: this.state.campTelefone,
            u_idutilizador: 31         
        }
        console.log("Nome: " + this.state.campNome + "| email: " + this.state.campEmail + "| dataNascimento: " + this.state.campDataNasc + "| password: " + this.state.campPassword + "| passwordRepeat: " + this.state.campPasswordRepeat + "| Pais: " + this.state.selectPais + "| telefone: " + this.state.campTelefone );
          await  axios.post(baseUrl,datapost).then(response=>{
                if (response.data) {
                    Swal.fire({
                        title: 'Conta Criada',
                        text: "Receberá um email com a confirmação",
                        icon: 'success',
                        showCancelButton: false,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'ok, entendi'
                      }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.reload();
                        }
                      })
                      
                }else {
                    Swal.fire(
                        'Cancelado!',
                        'Erro ao criar conta.',
                        'error'
                    ).then(() =>{
                        window.location.reload();
                    })
                }}).catch(error=>{
                    alert("Error 34 "+error)
                })      
    }
        



    //Buscar os dados à Base de Dados
    componentDidMount(){
            
    }

    
    render(){
        if (localStorage.getItem('user') == null) {
        return (    
            <div class="container">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image">
                        <img id="imagemAdmin" src={ImagemLogin} alt="..."></img>
                    </div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Criar Conta</h1>
                            </div>
                            <Form class="user" onSubmit={this.handleLogin} ref={c => {this.form = c;}}>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <Input type="text" class="form-control form-control-user" id="nome"
                                            placeholder="* Nome" value={this.state.campNome} onChange={this.onChangeNome} validations={[required]} validations={[requiredTamanho]}/>
                                    </div>
                                    <div class="col-sm-6">
                                        <Input type="date" class="form-control form-control-user" id="datanascimento" 
                                        placeholder="Data Nascimento" value={this.state.campDataNasc} onChange={ (value)=> this.setState({campDataNasc: value.target.value})} validations={[required]}/>                                        
                                    </div>
                                </div>
                                <div class="form-group row">                                     
                                        <div class="col-sm-6 mb-3 mb-sm-0 selectContainer">
                                            <div class="input-group">   
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>                                                    
                                                    <select name="state" class="form-control selectpicker" onChange={ (value)=> this.setState({selectPais: value.target.value})} validations={[required]}>
                                                        <option select>País</option>                                                        
                                                        <option value="1">Portugal</option>              
                                                        <option value="2">Afeganistão</option>
                                                        <option value="3">Tawai</option>
                                                    </select>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <Input type="number" class="form-control form-control-user" id="exampleInputEmail"
                                            placeholder="* Telefone" value={this.state.campTelefone} onChange={ (value)=> this.setState({campTelefone: value.target.value})} validations={[required]}/>
                                        </div>
                                </div>
                                <div class="form-group">
                                    <Input type="email" class="form-control form-control-user" id="exampleInputEmail"
                                        placeholder="* Email" value={this.state.campEmail} onChange={ (value)=> this.setState({campEmail: value.target.value})} validations={[required]}/>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <Input type="password" class="form-control form-control-user"
                                            id="exampleInputPassword" placeholder="* Password" onChange={this.onChangePassword} validations={[required]}/>
                                    </div>
                                    <div class="col-sm-6">
                                        <Input type="password" class="form-control form-control-user"
                                            id="exampleRepeatPassword" onChange={this.onChangePasswordRepeat} validations={[required]} placeholder="* Repetir Password" />
                                    </div>
                                </div>
                                <h6>* preenchimento obrigatório</h6>
                                <br></br>
                                <div className="form-group">
                                            <button className="btn btn-primary btn-block" disabled={this.state.loading}> {this.state.loading && ( <span className="spinner-border spinner-border-sm"></span>)}
                                                <span> Registar Conta</span>
                                            </button>
                                        </div>
                                {this.state.message && (<div className="form-group"> <div className="alert alert-danger" role="alert"> {this.state.message} </div> </div>)}
                                            <CheckButton style={{display:"none"}} ref={c =>{this.checkBtn=c;}}/>                                                 
                            </Form>
                            <hr />
                            <div class="text-center">
                                <a class="small" href="/login">Já tenho conta ? Login!</a>
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
export default Perfil;