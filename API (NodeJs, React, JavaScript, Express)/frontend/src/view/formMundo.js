import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';
class EditComponent extends React.Component{
constructor(props){
super(props);
this.state = {
campName: ""
}
}
render(){
    return (
    <div>
    <div class="form-row justify-content-center">
    <div class="form-group col-md-6">
    <label for="inputPassword4">Name </label>
    <input type="text" class="form-control"
    placeholder="Name" value={this.state.campName} onChange={(value)=> this.setState({campName:value.target.value})}/>
    </div>
    </div>
    <button type="submit" class="btn btn-primary"
    onClick={()=>this.sendSave()}>Save</button>
    </div>
    );
}
sendSave(){
    if (this.state.campName==="") {
        alert("Choose Nome!")
    }else {
        const baseUrl = "http://localhost:3000/mapa/create"
        const datapost = {
            m_nome : this.state.campName
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