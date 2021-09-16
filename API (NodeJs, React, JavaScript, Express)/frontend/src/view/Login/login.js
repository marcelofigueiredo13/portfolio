import React, { Component } from "react";

import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import ImagemLogin from '../images/login.png'
import './login.css'
import AuthService from "../auth.service";

import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

const required = value => {
if (!value) {
return (
        <div className="alert alert-danger" role="alert">
            Este campo é de preenchimento obrigatório!
        </div>
);
}
};
export default class Login extends Component {
    constructor(props) {
    super(props);
    this.handleLogin = this.handleLogin.bind(this);
    this.onChangeUsername = this.onChangeUsername.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.state = {
    username: "",
    password: "",
    loading: false,
    message: ""
    };
    }
    
    onChangeUsername(e) {
        this.setState({username:e.target.value});
        this.setState({loading:false});
    }
    
    onChangePassword(e) {
        this.setState({password: e.target.value});
        this.setState({loading:false});
    }

   async handleLogin(e) {
        e.preventDefault();
        this.setState({message: "", loading: true});
        this.form.validateAll();

        if (this.checkBtn.context._errors.length === 0) {     
               await AuthService.login(this.state.username, this.state.password).then((result) => {                           
                        if(result != 0){
                        this.props.history.push("/");
                        window.location.reload();                                                            
                        }else{
                            Swal.fire(
                                'Falha!',
                                'dados de autenticação inválidos',
                                'error'
                              )  
                        }
                }
            );

            this.setState({
                loading: false,
                message: "Tente Novamente"
            });
        }
    }

        render() {
            if (localStorage.getItem('user')=== null) {
            return (            

    <div class="container">


        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
              
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image">
                                <img id="imagemlogin" src={ImagemLogin} alt="..."></img>
                            </div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Bem-vindo</h1>
                                    </div>
                                    <br></br>
                                    <Form class="user" onSubmit={this.handleLogin} ref={c => {this.form = c;}}>                                    
                                        <div class="form-group">
                                        <Input type="text" placeholder="Enter Email Address..." className="form-control form-control-user" name="username" value={this.state.username} onChange={this.onChangeUsername} validations={[required]} />                                            
                                        </div>
                                        <div class="form-group">
                                        <Input type="password" placeholder="Password" className="form-control form-control-user" name="password" value={this.state.password} onChange={this.onChangePassword} validations={[required]} />                                           
                                        </div>                                                                                                           
                                        <hr/>                                                                                                               
                                        <div className="form-group">
                                            <button className="btn btn-primary btn-block" disabled={this.state.loading}> {this.state.loading && ( <span className="spinner-border spinner-border-sm"></span>)}
                                                <span> Login</span>
                                            </button>
                                        </div>
                                        {this.state.message && (<div className="form-group"> <div className="alert alert-danger" role="alert"> {this.state.message} </div> </div>)}
                                            <CheckButton style={{display:"none"}} ref={c =>{this.checkBtn=c;}}/>
                                    </Form>                                                                     
                                    <div class="text-center">
                                        <a class="small" href="/criarconta">Criar Conta!</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>
    
   
            );
            }
}
}
