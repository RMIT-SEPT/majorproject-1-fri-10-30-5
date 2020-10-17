import React, { Component } from "react";
import axios from "axios";
import { authenticate } from "../../../actions/auth";

export default class AddService extends Component {
  constructor(props) {

    super(props);
    this.state = {
      serviceId: "",
      name:"",
      description:"",
      duration:"",

      user: authenticate()
    };
  }

  myChangeHandler = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  mySubmitHandler = (event) => {
    event.preventDefault();

    const service = {
      serviceId:this.state.serviceId,
      name:this.state.name,
      description:this.state.description,
      duration:this.state.duration
    };
    console.log(service);

    axios
      .post("http://localhost:8080/api/service/add", service)
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
          <form id="addServiceForm" onSubmit={this.mySubmitHandler}>
            <h3>Add Service</h3>
            <div className="form-group">
            <label htmlFor="serviceId">Service ID</label>
            <input
              name="serviceId"
              type="number"
              className="form-control"
              onChange={this.myChangeHandler}
              placeholder="Service Id"
              required
            />
          </div>
            <div className="form-group">
              <label htmlFor="name">Service name</label>
              <input
                name="name"
                type="text"
                className="form-control"
                onChange={this.myChangeHandler}
                placeholder="Service name"
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="description">Description</label>
              <textarea
                name="description"
                className="form-control"
                type="textarea"
                rows="3"
                onChange={this.myChangeHandler}
                placeholder="Description"
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="duration">Duration (mins)</label>
              <input
                name="duration"
                className="form-control"
                type="number"
                onChange={this.myChangeHandler}
                placeholder="Duration"
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
              Add
            </button>
          </form>
        </div>
      </div>
    );
  }
}
