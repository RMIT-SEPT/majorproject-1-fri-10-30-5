import React, { Component } from "react";
import axios from "axios";
import { authenticate } from "../../../actions/auth";
const createHistory = require("history").createBrowserHistory;

export default class AddEmployee extends Component {
  constructor(props) {
    super(props);

    this.state = {
      firstName: "",
      lastName: "",
      address: "",
      phone: "",
      userName: "",
      pw: "",

      user: authenticate(),
      errorDisplay: false
    };
  }

  myChangeHandler = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  addUser() {

    const newUser = {
        userName: this.state.userName,
        userType: "employee",
        password: this.state.pw,
        confirmPassword: this.state.pw
    }
    console.log(newUser);

    // const baseurl = 'http://ec2-52-203-27-92.compute-1.amazonaws.com:8080'
    const baseurl = 'http://localhost:8080'
    axios.post(baseurl + '/api/user/register',
        newUser
    )
        .then(res => //showOutput(res))
        {
            let history = createHistory();
            history.push("/workerSuccess");
            let pathUrl = window.location.href;
            window.location.href = pathUrl; 
        })
        .catch(err => {
          console.error(err)
          this.setState({ errorDisplay: true })
      });
  }

  mySubmitHandler = (event) => {
    event.preventDefault();

  
    const employee = {
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      address: this.state.address,
      phone: this.state.phone,
      userName: this.state.userName,
      password: this.state.pw,
      userType: "employee"
    };
    console.log(employee);
    
    axios
      .post("http://localhost:8080/api/person/add", employee)
      .then(
        (
          res //showOutput(res))
        ) => {
          console.log(res);
          console.log(res.data);

          this.addUser()
        }
      )
      .catch((err) => console.error(err));
  };

  

  render() {
    // admin validation

    return (
      <div className="forms-wrapper">
        <div className="forms-inner">
          <form id="addEmployeeForm" onSubmit={this.mySubmitHandler}>
            <h3>Add New Employee</h3>
            <div className="form-group">
              <label htmlFor="firstName">First name</label>
              <input
                name="firstName"
                type="text"
                className="form-control"
                onChange={this.myChangeHandler}
                placeholder="First name"
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="lastName">Last name</label>
              <input
                name="lastName"
                className="form-control"
                type="text"
                onChange={this.myChangeHandler}
                placeholder="Last name"
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="userName">Username</label>
              <input
                name="userName"
                className="form-control"
                type="text"
                onChange={this.myChangeHandler}
                placeholder="Username"
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="address">Address</label>
              <input
                name="address"
                className="form-control"
                type="text"
                onChange={this.myChangeHandler}
                placeholder="Address"
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="phone">Phone</label>
              <input
                name="phone"
                className="form-control"
                type="Phone"
                onChange={this.myChangeHandler}
                placeholder="Phone number"
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="pw">Password</label>
              <input
                name="pw"
                className="form-control"
                type="password"
                onChange={this.myChangeHandler}
                placeholder="Password"
                required
              />
            </div>
            <button
              type="submit"
              className="btn btn-primary btn-block"
              style={{
                backgroundColor: "#341930",
                border: "1px solid #341930",
              }}
            >
              Add Employee
            </button>
          </form>
          <p className="error" style={{display: this.state.errorDisplay ? 'block' : 'none', color:'red', textAlign:'center' }}>Employee registration failed.</p>
        </div>
      </div>
    );
  }
}
