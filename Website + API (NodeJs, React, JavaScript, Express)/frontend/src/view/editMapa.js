import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';
const baseUrl = "http://localhost:3000";
class EditComponent extends React.Component{
    constructor(props){
    super(props);
        this.state = {
            dataUtilizador:{},
                campNome: ""
            }
    }

    //Buscar os dados à Base de Dados
    componentDidMount(){
            let mapaId = this.props.match.params.m_idmapa;
            console.log(mapaId);
                const url = baseUrl+"/mapa/get/"+mapaId
    axios.get(url).then(res=>{
            if (res.data.success) {
                    const data = res.data.data[0]
                        this.setState({
                                dataMapa:data,
                                campNome: data.m_nome,
  
                        })
            }else {
                alert("Error web service")
            }
    }).catch(error=>{
                alert("Error server: "+error)
    })
    }

    //atualizar dados
    sendUpdate(){
        // get parameter id
        let mapaId = this.props.match.params.m_idmapa;
        // url de backend
        const url = baseUrl+"/mapa/update/"+ mapaId
        // parametros de datos post
        const datapost = {
                m_nome : this.state.campNome
        }
        axios.post(url,datapost).then(response=>{
                if (response.data.success===true) {
                    alert(response.data.message)
                }else {
                    alert("Error")
                }}).catch(error=>{
                    alert("Error 34 "+error)})
    }
    
    render(){
    return (
        
        <div>
        <div class="form-row justify-content-center">
        <div class="form-group col-md-6">
        <label for="inputPassword4">Name </label>
        <input type="text" class="form-control"
        placeholder="Nome" 
        value={this.state.campNome} onChange={(value)=> this.setState({campNome:value.target.value})}/>
        </div>

        </div>

        <div class="mt-4">
        <button type="submit" class="btn btn-primary" onClick={()=>this.sendUpdate()}>Update</button>
        </div>

</div>
);
}
}
export default EditComponent;