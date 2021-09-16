import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';
class EditComponent extends React.Component{
constructor(props){
super(props);
this.state = {
campTipoAlerta: "",
campId:0
}
}
render(){
    return (
    <div>
    <div class="form-row justify-content-center">
    <div class="form-group col-md-6">
    <label for="inputPassword4">Alerta </label>
    <input type="text" class="form-control"
    placeholder="Name" value={this.state.campTipoAlerta} onChange={(value)=>
    this.setState({campTipoAlerta:value.target.value})}/>
    </div>
    </div>
    <div class="form-row">

    <div class="form-group col-md-6">
    <label for="inputEmail4">ID</label>
    <input type="number" class="form-control"
    placeholder="Phone" value={this.state.campId} onChange={(value)=> this.setState({campId:value.target.value})}/>
    </div>
    </div>

    <button type="submit" class="btn btn-primary"
    onClick={()=>this.sendSave()}>Save</button>
    </div>
    );
    }
    sendSave(){
    if (this.state.selectRole===0) {
    alert("Choose Role!")
    }
    else if (this.state.campPhone==="") {
    alert("Insert the Phone!")
    }
    else if (this.state.campName==="") {
    alert("Insert Name!")
    }
    else if (this.state.campEmail==="") {
    alert("Insert Email!")
    }
    else if (this.state.campAddress==="") {
    alert("Insert Address!")
    }
    else {
    const baseUrl = "http://localhost:3000/tipoalerta/create"
    const datapost = {
           ta_idtipo: this.state.campId,
           ta_designacao : this.state.campTipoAlerta,
    }
    axios.post(baseUrl,datapost)
    .then(response=>{
    if (response.data.success===true) {
    alert(response.data.message)
    }
    else {
    alert(response.data.message)
    }
    }).catch(error=>{
    alert("Error 34 "+error)
    })
    }
    }
    }
    export default EditComponent;