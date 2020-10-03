import React, { Component } from "react";
import axios from "axios";

export default class AddEmployee extends Component {
  constructor(props) {
    super(props);
    const admin = this.props.user.username;

    this.state = {
      admin: admin,
      firstName: "",
      lastName: "",
      address: "",
      phone: "",
      userName: "",
      pw: "",
    };
  }

  myChangeHandler = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  mySubmitHandler = (event) => {
    event.preventDefault();

  
    const employee = {
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      address: this.state.address,
      phone: this.state.phone,
      userName: this.state.userName,
      password: this.state.pw,
      admin: this.state.admin,
      employeeCheck: true,
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
        </div>
      </div>
    );
  }
}