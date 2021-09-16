import React, { Component } from "react";
import ImagemError from '../images/error.png'
import './error.css'




export default class Error extends Component {
    constructor(props) {
    super(props);

    }
    

        render() {
            return (                
                <div class="container-fluid">
                    <div class="text-center">
                        <div class="error mx-auto" data-text="404"> <img id="imagemError" src={ImagemError}></img></div>
                        <p class="lead text-gray-800 mb-5">Acesso Negado</p>
                        <p class="text-gray-500 mb-0">É necessário realizar autenticação na aplicação...</p>
                        <br></br>
                        <a href="/login">&larr; Login</a>
                    </div>
                </div>
            );
}
}
