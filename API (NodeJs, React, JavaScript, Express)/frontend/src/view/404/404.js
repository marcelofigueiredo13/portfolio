import React, { Component } from "react";



export default class PageNotFound extends Component {
    constructor(props) {
    super(props);

    }
    

        render() {
            return (                
                <div class="container-fluid">
                    <div class="text-center">
                        <div class="error mx-auto" data-text="404"> </div>
                        <p class="lead text-gray-800 mb-5">Página não encontrada</p>
                        <p class="text-gray-500 mb-0">Voltar à aplicação</p>
                        <br></br>
                        <a href="/home">&larr; Página Principal</a>
                    </div>
                </div>
            );
}
}