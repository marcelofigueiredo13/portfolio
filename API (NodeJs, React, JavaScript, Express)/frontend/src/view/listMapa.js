import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { Link } from "react-router-dom";
import axios from 'axios';

//sweetalert2 - importação
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'


class listComponent extends React.Component {
constructor(props){
super(props);
this.state = {
ListaMapa:[]
}
}
componentDidMount(){
    this.loadUtilizador();
}

loadUtilizador(){
    const url = "http://localhost:3000/mapa/list";
    axios.get(url).then(res => {
    if(res.data.success){
        const data = res.data.data;
        this.setState({ ListaMapa:data });
    }else{
        alert("Error Web Service!");
    }
    }).catch(error => {
        alert(error)
    });
}
render()
{
return (
<table className="table table-hover table-striped">
<thead className="thead-dark">
<tr>
<th scope="col">ID</th>
<th scope="col">Nome</th>
</tr>
</thead>
<tbody>
{this.loadFillData()}
</tbody>
</table>

);
}

sendDelete(id){
        // url do backend
        const baseUrl = "http://localhost:3000/mapa/delete"
        // network
        axios.post(baseUrl,{
            m_idmapa:id
        }).then(response =>{
            if (response.data.success) {
                Swal.fire(
                    'Deleted!',
                    'Your employee has been deleted.',
                    'success')
                    this.loadUtilizador()
            }
        }).catch ( error => {
            alert("Error 325 ")
        })
}

onDelete(id){
    Swal.fire({
    title: 'Are you sure?',
    text: 'You will not be able to recover this imaginary file!',
    type: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes, delete it!',
    cancelButtonText: 'No, keep it'
    }).then((result) => {
    if (result.value) {
    this.sendDelete(id)
    } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
            'Cancelled',
            'Your imaginary file is safe :)',
            'error'
        )
    }
    })
    }

loadFillData(){
    return this.state.ListaMapa.map((data, index)=>{
    return(
    <tr key={index}>
    <th>{data.m_idmapa}</th>
    <th>{data.m_nome}</th>
    <td>
    <Link class="btn btn-outline-info " to={"/editMapa/"+data.m_idmapa} >Edit</Link>
    </td>
    <td>
    <button class="btn btn-outline-danger" onClick={()=>this.onDelete(data.m_idmapa)}> Delete </button>
    </td>
    </tr>
    )
    });
    }
    }
export default listComponent;