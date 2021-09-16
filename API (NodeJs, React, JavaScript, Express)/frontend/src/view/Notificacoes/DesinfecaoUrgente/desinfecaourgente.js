import React from "react";
import { GoogleMap, withScriptjs, withGoogleMap, Marker, InfoWindow} from "react-google-maps"
import Error from '../../Error/error';
import './desinfecaourgente.css'
import axios from 'axios';
import authHeader from '../../auth-header';

//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

function Map(){
    var lati = localStorage.getItem('latitude');

          
            var longi = localStorage.getItem('longitude');

  return ( 
  <GoogleMap 
    defaultZoom={10} 
    defaultCenter={{ lat: JSON.parse(lati), lng: JSON.parse(longi)}}
    >
        <Marker 
            position={{
                lat:JSON.parse(lati),
                lng:JSON.parse(longi)
            }}
            
        />        
    </GoogleMap>
  );
}

const WrappedMap = withScriptjs(withGoogleMap(Map));

export default class App extends React.Component{
    constructor(props){
        super(props);
        this.state = {
        ListaReport:[],
        ListaAlerta:[],
        campNome: "",
        campIdAlerta: "",
        local: 0  
        }
    }

    async componentDidMount(){        
       await this.local();
       await this.loadAlerta();
    }
    
    //Encontrar Local pelas coordenadas
    async local(){
        const url = "https://crowdzeroapieuropeappandsite.herokuapp.com/local/list";
       await axios.get(url).then(res => {
        if(res.data){
            const data = res.data;
            this.setState({ 
              ListaLocal:data,  
            });            
            this.state.local = localStorage.getItem("Local")
            this.loadLocalDesinfecaoo();
        }else{
            alert("Falha ao carregar os Locais !");
        }
        }).catch(error => {
            alert(error)
        });    
      }


    loadLocalDesinfecaoo(){
        return this.state.ListaLocal.map((data)=>{            
            if(data.l_idlocal == this.state.local){                 
                const dataNome = data.l_nome;                                
                this.setState({ campNome:dataNome});                             
            }
        });
    }

    //Get ID do alerta para fazer update do estadp do alerta
    async loadAlerta(){
        const url = "https://crowdzeroapieuropeappandsite.herokuapp.com/alerta/list";
      await axios.get(url, {headers: authHeader()}).then(res => {
    if(res.data.success){
        const data = res.data.data;
        this.setState({ ListaAlerta:data });
        this.state.ListaAlerta.map((data)=>{            
            if(data.tipoalertum.ta_idtipo === 2){                                                
                const dataIdAlerta = data.al_idalerta;                                
                this.setState({ campIdAlerta:dataIdAlerta});                
            }
        });
    }else{
        alert("Falha ao Carregar os Alertas! ");
    }
    }).catch(error => {
        alert(error)
    });
    }


   async EnviarEquipa(){
        let alertaId = this.state.campIdAlerta;
        const baseUrl = "https://crowdzeroapieuropeappandsite.herokuapp.com";
        const url = baseUrl+"/alerta/update/"+ alertaId
        const datapost = {
                ta_idtipo : 5                
        }
       await axios.post(url,datapost).then(response=>{
                if (response.data.success===true) {                                                            
                    Swal.fire({
                        title: 'Enviado!',                        
                        type:'sucess',                        
                    }).then((result) => {
                        if (result.value) {
                            this.props.history.push("/");
                            window.location.reload()
                            localStorage.removeItem("longitude");
                            localStorage.removeItem("latitude");
                            localStorage.removeItem("Local");
                        }     
                    })    
                    }else {
                        Swal.fire(
                            'Falha ao enviar equipa de desinfeção',
                            'warning'
                        )
                    }                                    
        });
    }

  async  BotaoEnviar(){
        Swal.fire({
            title: 'Enviar Equipa de Desinfeção ?',
            type: 'info',
            showCancelButton: true,
            confirmButtonText: 'Sim, enviar',
            cancelButtonText: 'Não'
            }).then((result) => {
            if (result.value) {
                this.EnviarEquipa();           
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                Swal.fire(
                    'Cancelado!',
                    'A equipa de desinfeção não foi enviada para o local.',
                    'warning'
                )            
            }
            })
    }
    
    render(){
        if (localStorage.getItem('user')!= null) {
  return (
    <div id="cont" class="container">
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
          Desinfeção Urgente
        </h3>
        </div>
    </div>
    <hr></hr>
        <br></br>              
      <div>
      <WrappedMap googleMapURL={`https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyDyy_EwhzTJLy1gW91GhRadcttukl6V3OQ`}
          loadingElement = {<div style={{ height: `100%` }} />}
          containerElement={<div style={{ height: `400px` }} />}
          mapElement={<div style={{ height: `100%` }} />}
        />
      </div>
    <div class="row mt-5">
        <div class="col-md-6">
        <p id="pergunta">Enviar uma equipa de Desinfeção ?</p>
        </div>
    </div>
    <div>
        <div class="form-row justify-content-center">
        <div class="form-group col-md-6">
                <label for="inputPassword4">Local</label>
                <input type="text" class="form-control" value={this.state.campNome} placeholder="Nome"/>
                <br></br>
                <label for="inputPassword4">Data de Desinfeção</label>
                <input type="date" class="form-control" placeholder="data" />                
        </div>                
        <div class="form-group col-md-6">                                        
                <label for="inputPassword4">Latitude</label>
                <input type="text" class="form-control" value={JSON.parse(localStorage.getItem('latitude'))} placeholder="Latitude"/>
                <br></br>
                <label for="inputPassword4">Longitude</label>
                <input type="text" class="form-control" value={JSON.parse(localStorage.getItem('longitude'))} placeholder="Longitude"/>
        </div>
        </div>            

        

        <div class="mt-4">
        <button id="botao" type="submit" class="btn btn-primary" onClick={()=>this.BotaoEnviar()}>Enviar</button>
        </div>

</div>
</div>
    
  )
}else{
  return (
    <Error />
  )
}
   } 
}