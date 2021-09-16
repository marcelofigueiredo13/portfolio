import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';

//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

import Error from '../Error/error';

const baseUrl = "https://crowdzeroapieuropeappandsite.herokuapp.com";
class EditComponent extends React.Component{
    constructor(props){
    super(props);
        this.state = {
            dataLocal:{},
                campNome: "",
                campLatitude: "",
                campLongitude: "",
                loading: true                
            }
    }

    //Buscar os dados à Base de Dados
    async componentDidMount(){
           await this.MostrarDados();
    }

    async MostrarDados(){    
        let localId = this.props.match.params.l_idlocal;
        console.log(localId);
            const url = baseUrl+"/local/get/"+ localId
            await axios.get(url).then(res=>{
            if (res.data) {
                this.state.loading = false;
                const data = res.data[0]
                    this.setState({
                        dataLocal:data,
                            campLatitude: data.l_latitude,
                            campLongitude: data.l_longitude,
                            campNome: data.l_nome
                    })
                    
        }else {
            alert("Mostrar Dados")
        }}).catch(error=>{
            alert("Error server: "+error)
        })  
    }

    //atualizar dados
  async  sendUpdate(){
        let localId = this.props.match.params.l_idlocal;
        const url = baseUrl+"/local/update/"+ localId
        const datapost = {
                l_nome : this.state.campNome,
                l_longitude : this.state.campLongitude,
                l_latitude : this.state.campLatitude
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

    onUpdate(){
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
                <a href="/listalocais">
                    <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                        <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
                    </svg>   
                </a>
                <div class="col-6 justify-content-center">        
                <h3>
                  Editar Local
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
        <a href="/listalocais">
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
        </div>                
        <div class="form-group col-md-6">        
                <label for="inputPassword4">Longitude</label>
                <input type="number" class="form-control" placeholder="Longitude"  value={this.state.campLongitude} onChange={(value)=> this.setState({campLongitude:value.target.value})}/>
                <br></br>
                <label for="inputPassword4">Latitude</label>
                <input type="number" max="50" min="0" class="form-control" placeholder="Latitude"  value={this.state.campLatitude} onChange={(value)=> this.setState({campLatitude:value.target.value})}/>
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