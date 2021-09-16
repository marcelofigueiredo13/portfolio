import React, {useState} from "react";
import { GoogleMap, withScriptjs, withGoogleMap, Marker, InfoWindow} from "react-google-maps"
import Error from '../Error/error';
import  pinos from "./pinos.json";

import '../Login/login.css'

function Map(){
  const [selecionadoPino, setPinoSelecionado] = useState(null);
  
  return ( 
    <GoogleMap 
      defaultZoom={14} 
      defaultCenter={{ lat: 40.656586, lng: -7.912471}}
    >
      {pinos.map((pinos) =>(
        <Marker        
          key={pinos.r_idreport}
          position={{
            lat: pinos.local.l_latitude,
            lng: pinos.local.l_longitude
          }}
          onClick={() =>{
            setPinoSelecionado(pinos);
          }}
        />
      ))}

      {selecionadoPino && (
        <InfoWindow
        position={{
          lat: selecionadoPino.local.l_latitude,
          lng: selecionadoPino.local.l_longitude
        }}
          onCloseClick={() =>{
            setPinoSelecionado(null);
          }}
        >
            
            <div>{selecionadoPino.n_idnivel === 1 &&               
              <p>
                  <b>Pouco populado</b>
                  <br></br>
                  {selecionadoPino.local.l_nome}
              </p>
            }
              {selecionadoPino.n_idnivel === 2 && 
              <p>
                 <b> Muito populado</b>
                 <br></br>
                  {selecionadoPino.local.l_nome}
              </p>              
            }{selecionadoPino.n_idnivel === 3 && 
              <p>
                  <b>Extramamente populado</b>
                  <br></br>
                  {selecionadoPino.local.l_nome}
              </p>
            }
            </div>
        </InfoWindow>
      )}
    </GoogleMap>
  );
}

const WrappedMap = withScriptjs(withGoogleMap(Map));

export default function App(){
  if (localStorage.getItem('user')!= null) {
  return (
    <div class="container">
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
          Mapa do Mundo
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
      <div className="container">
      <div className="row">
        
      <p id="filtrar">Filtrar por Datas</p>
            <svg xmlns="http://www.w3.org/2000/svg" id="setabaixo" width="25" height="25" fill="currentColor" class="bi bi-arrow-down-square" viewBox="0 0 16 16">
                  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm8.5 2.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z"/>
            </svg></div></div>
        <div class="form-group row">
        <div class="col-sm-3 mb-3 mb-sm-0">
            <input type="date" class="form-control form-control-user" id="nome"/>
        </div>
                                    
        </div>
        <div class="row just">
            <div id="botaoInserirLocal" className="col-md-2 col-md-offset-5">
                 <button className="btn btn-primary btn-block">
                      <span>Pesquisar</span>
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