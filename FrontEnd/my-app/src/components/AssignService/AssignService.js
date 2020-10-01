import axios from 'axios'
import React, { Component } from 'react'

class AssignService extends Component {

  state ={
    services:[],
    employees:[],
    serviceId:'',
    userName:''
  }

  componentDidMount(){
    axios.get('http://localhost:8080/api/person/employee/list/')
      .then(res => {
        this.setState({
          employees: res.data,
          userName: res.data[0].userName
        })
      })
      .catch((error) => {
        console.log("error",error)
      })  
      
      axios.get('http://localhost:8080/api/service/list/')
        .then(res => {
          this.setState({
            services: res.data,
            serviceId: res.data[0].serviceId
          })
        })
        .catch((error) => {
          console.log("error",error)
        }) 
}

mySubmitHandler = event => {
  const assignService = {
      serviceId: this.state.serviceId,
      userName: this.state.userName
  }

  axios.post('http://localhost:8080/api/assignService/add', 
  assignService
    )
    .then(res => 
    {console.log(res.data);
   })
    .catch(err => console.error(err));
}

myChangeHandler = event => {
  this.setState({[event.target.name]: event.target.value})
};

render(){
  return (
    <div className="drop-down">
        <p>Employee User</p>
            <select name = "userName"  onChange = {this.myChangeHandler}>{
              this.state.employees.map((employee,index) => <option key={index} value={employee.userName} >{employee.userName}  </option>)}
            </select>
            <br></br>
        <p>Service Name</p>
              <select  name = "serviceId"   onChange = {this.myChangeHandler}>{
                this.state.services.map((service,index) => <option key={index} value= {service.serviceId} >{service.name}</option>) }
              </select>
        <br></br>
        <br></br>
        <button className="btn btn-primary btn-sm m-2"
          onClick={() => {
            this.mySubmitHandler();
        }}
      >
        Assign Service
      </button>
        </div>
  )
}

}

export default AssignService;
