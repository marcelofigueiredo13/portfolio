import React from 'react';
import './footer.css'
const { Component } = require("react")

const baseUrl = "https://crowdzeroapieuropeappandsite.herokuapp.com";
export default class Footer extends Component{

  constructor(props) {
    super(props);
    this.state = {
             
      }
  }

  
render(){        
    return (

    <footer id="footer" class="text-center text-lg-start bg-dark text-muted">
        <section class="">
            <div class="container text-center text-md-start mt-5">Poderá realizar o download da aplicação moblie em: </div>
            https://estgv-my.sharepoint.com/:u:/g/personal/estgv17734_alunos_estgv_ipv_pt/ET3u1YbZ0g1OjhmD5zhHVJUBj5tL5S9dIGyuNC-BUqjI6g?e=WhcSsp
            <div class="text-center p-4">© 2021 Copyright:
            <a class="text-reset fw-bold" href="https://mdbootstrap.com/">João Caldas & João Marcelo</a>
        </div>
        </section>                          
    </footer>
    );    
}
}