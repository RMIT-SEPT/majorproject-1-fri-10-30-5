import axios from 'axios'
import React, { Component } from 'react'
import { authenticate } from '../../../actions/auth'

class AssignService extends Component {

  state = {
    services: [],
    employees: [],
    serviceId: '',
    userName: '',

    user: authenticate(),
    errorDisplay: false
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/person/employee/list/')
      .then(res => {
        this.setState({
          employees: res.data,
          userName: res.data[0].userName
        })
      })
      .catch((error) => {
        console.log("error", error)
      })

    axios.get('http://localhost:8080/api/service/list/')
      .then(res => {
        this.setState({
          services: res.data,
          serviceId: res.data[0].serviceId
        })
      })
      .catch((error) => {
        console.log("error", error)
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
      .then(res => {
        console.log(res.data);
      })
      .catch(err => {
        console.error(err)
        this.setState({ errorDisplay: true })
    });
  }

  myChangeHandler = event => {
    console.log(event.target.name, event.target.value)
    this.setState({ [event.target.name]: event.target.value })
  };

  render() {
    return (
      <div className="forms-wrapper">
        <div className="forms-inner">
          <form id="assignServiceForm">
            <h3>Assign Service</h3>
            <div className="form-group">
            <label htmlFor="userName">Employee:</label>
            <select name="userName" className="form-control"
              onChange={this.myChangeHandler}>{
                this.state.employees.map((employee, index) => <option key={index} value={employee.userName} >{employee.userName}  </option>)}
            </select>
            </div>
            <div className="form-group">
            <label htmlFor="serviceId">Service name:</label>
            <select name="serviceId" className="form-control" onChange={this.myChangeHandler}>{
              this.state.services.map((service, index) => <option key={index} value={service.serviceId} >{service.name}</option>)}
            </select>
            </div>
            <button className="btn btn-primary btn-block" style={{ backgroundColor: "#341930", border: "1px solid #341930" }}
              onClick={() => {
                this.mySubmitHandler();
              }}
            >
              Assign Service
</button>

          </form>
          <p className="error" style={{display: this.state.errorDisplay ? 'block' : 'none', color:'red', textAlign:'center' }}>Service assignment failed.</p>
        </div>
      </div>

    )
  }

}

export default AssignService;
