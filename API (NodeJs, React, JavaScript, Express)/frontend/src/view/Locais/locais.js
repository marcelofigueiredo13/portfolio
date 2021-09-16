import React from "react";
import { GoogleMap, withScriptjs, withGoogleMap, Marker, InfoWindow} from "react-google-maps"
import Error from '../Error/error';

import axios from 'axios';

//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

const WrappedMap = withScriptjs(withGoogleMap(Map));
    
function Map(){
    const [markers, setMarkers] = React.useState([]);
    const [selected, setSelected] = React.useState(null);
    const mapRef = React.useRef();
    const onMapLoad = React.useCallback((map) =>{mapRef.current = map;}, []);
    
    return (    
        <GoogleMap 
          defaultZoom={10} 
          defaultCenter={{ lat: 40.656586, lng: -7.912471}}
          onClick={(event)=>{                
              setMarkers(current =>[...current,{
                  lat: event.latLng.lat(),
                  lng: event.latLng.lng(),
                  time: new Date(),                
              }])                                             
                
          }}
          onLoad={onMapLoad}
          >
              {markers.map((marker) =>(
                  <Marker key={marker.time.toISOString()} 
                  position={{
                      lat: marker.lat,
                      lng: marker.lng
                  }}
                  onClick={()=>{
                      setSelected(marker);
                  }} />
              ))}

              {selected ?(<InfoWindow
                    position= {{ lat: selected.lat, lng: selected.lng}}
                    onCloseClick={()=>{
                        setSelected(null);
                    }}                    
                   >
                    <div>
                          <h6>Latitude: {selected.lat}</h6>
                          <h6>Longitude: {selected.lng}</h6>
                      </div>  
                  </InfoWindow>
                
                
              ): null}
        </GoogleMap>
        );
      }

class Local extends React.Component{
    constructor(props){
        super(props);
            this.state = {                
                loading: true,
                campNome: "",
                campLat: "",
                campLng: ""
            }
        
    }



    //Enviar um novo local
    async  InserirLocal(){
        const url = "https://crowdzeroapieuropeappandsite.herokuapp.com/local/create";
        const datapost = {
                l_nome : this.state.campNome,
                l_longitude : this.state.campLng,
                l_latitude : this.state.campLat
        }
       await axios.post(url,datapost).then(response=>{
                if (response.data) {
                    Swal.fire(
                        'Inserido',
                    )                    
                }else {
                    console.log("Falhou");
                }
        });
    }


    //Confirmação criação de local
    Alerta(){
        Swal.fire({
        title: 'Inserir novo local ?',
        type: 'info',
        showCancelButton: true,
        confirmButtonText: 'Sim, inserir',
        cancelButtonText: 'Não'
        }).then((result) => {
        if (result.value) {
            this.InserirLocal();           
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire(
                'Cancelado!',
                'O local não foi inserido!',
                'info'
            )            
        }
        })
    }

render(){
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
          Definir Locais
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
      <br></br>
      <br></br>
      <p>Adicionar um novo Local</p>
      <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="nome"
                                            placeholder="* Nome" value={this.state.campNome} onChange={(value)=> this.setState({campNome:value.target.value})}/>
                                    </div>
                                    
        </div>
        <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="number" class="form-control form-control-user" id="latitude"
                                            placeholder="* Latitude" value={this.state.campLat} onChange={(value)=> this.setState({campLat:value.target.value})}/>
                                    </div>
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="nu,ber" class="form-control form-control-user" id="longitude"
                                            placeholder="* Longitude" value={this.state.campLng} onChange={(value)=> this.setState({campLng:value.target.value})}/>
                                    </div>
                                    
        </div>
        <div class="row just">
            <div id="botaoInserirLocal" className="col-md-2 col-md-offset-5">
                 <button className="btn btn-primary btn-block" onClick={()=>this.Alerta()}>
                      <span>Inserir novo Local</span>
                 </button>
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

export default Local;