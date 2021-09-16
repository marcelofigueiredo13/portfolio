import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';
import { Bar} from 'react-chartjs-2'

//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

import Error from '../Error/error';



const baseUrl = "http://localhost:3000";
class Perfil extends React.Component{
    constructor(props){
    super(props);
        this.state = {
            dataUtilizador:{},
                campEmail: "",
                campNome: "",
                campDataNasc: "",
                campLikes: "",
                campPais: "",                
            }
    }

    //Buscar os dados à Base de Dados
    componentDidMount(){
            let userId = 1;
                const url = baseUrl+"/utilizador/get/"+ userId
    axios.get(url).then(res=>{
            if (res.data.success) {
                    const data = res.data.data[0]
                        this.setState({
                                dataMapa:data,
                                campEmail: data.u_email,
                                campNome: data.u_nome,
                                campDataNasc: data.u_datanascimento,
                                campLikes: data.u_likes,
                                campPais: data.pai.p_idpais,
                                campPaisNome: data.pai.p_nome
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
        let userId = this.props.match.params.u_idutilizador;
        // url de backend
        const url = baseUrl+"/utilizador/update/"+ userId
        // parametros de datos post
        const datapost = {
                u_email : this.state.campEmail,
                u_nome : this.state.campNome,
                u_datanascimento : this.state.campDataNasc,
                u_likes : this.state.campLikes,
                p_idpais : this.state.campPais
        }
        axios.post(url,datapost).then(response=>{
                if (response.data.success===true) {
                    Swal.fire(
                        'Atualizado',
                    )
                    
                }else {
                    console.log("Falhou");
                }
        });
    }

    onUpdate(id){
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
        if (localStorage.getItem('user')!= null) {
        return (
        
    <div className="container">
            <br></br>
            <br></br>
        <div className="row">
            <a href="/home">
                <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" className="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                        <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
                </svg>   
            </a>
        <div className="col-6 justify-content-center">        
                <h3>Semanalmente</h3>
        </div>
        </div>
            <hr></hr>
                <br></br>
    <div>         
    <Bar
        data={{
            labels: ['Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Domingo'],
            datasets:[{
                label: '# of votes',
                data: [12, 19, 3, 5, 2, 3, 4],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',                                        
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(153, 180, 255, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 220, 64, 1)'
                ]
            }]
        }}
        height={500}
        width={1000}
        options={{
            maintainAspectRadio: false
        }}
    />
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
export default Perfil;