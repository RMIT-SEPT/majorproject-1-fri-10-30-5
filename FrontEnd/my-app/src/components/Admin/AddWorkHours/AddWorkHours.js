import React, { Component } from "react";
import axios from "axios";
import Moment from "moment";
import { authenticate } from "../../../actions/auth";

export default class AddWorkHours extends Component {
    constructor(props) {
        super(props);

        this.state = {
            employees: [],
            services: [],
            empID: "",
            startTime: "",
            endTime: "",
            workDate: "",
            service: "",

            user: authenticate()
        };
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/person/employee/list')
            .then(res => {
                this.setState({
                    employees: res.data,
                    empID: res.data[0].empID
                })
            })
            .catch((error) => {
                console.log("error", error)
            })

        axios.get('http://localhost:8080/api/service/list/')
            .then(res => {
                this.setState({
                    services: res.data,
                    service: res.data[0].service
                })
            })
            .catch((error) => {
                console.log("error", error)
            })
    }

    myChangeHandler = (event) => {
        this.setState({ [event.target.name]: event.target.value });
    };

    mySubmitHandler = (event) => {
        event.preventDefault();

        const workHours = {
            empID: this.state.empID,
            startTime: this.state.startTime.replace(/:/g, ""),
            endTime: this.state.endTime.replace(/:/g, ""),
            workDate: this.state.workDate,
            service: this.state.service,
        };

        console.log(workHours);
        axios
            .post("http://localhost:8080/api/workinghours/add", workHours)
            .then((res) => {
                console.log(res);
                console.log(res.data);
            })
            .catch((err) => console.error(err));
    };

    render() {
        return (
            <div className="forms-wrapper">
                <div className="forms-inner">
                    <h3>Add Working Hours</h3>
                    <form id="addWorkHoursForm" onSubmit={this.mySubmitHandler}>
                        <div className="form-group">

                            <label htmlFor="empID">Employee</label>
                            <select name="empID" className="form-control"
                                onChange={this.myChangeHandler}>
                                {
                                    this.state.employees.map((employee, index) => <option key={index} value={employee.userName} >{employee.userName}  </option>)
                                }
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="startTime">Start time</label>
                            <input
                                name="startTime"
                                className="form-control"
                                type="time"
                                value={this.state.startTime}
                                onChange={this.myChangeHandler}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="endTime">End time</label>
                            <input
                                name="endTime"
                                className="form-control"
                                type="time"
                                value={this.state.endTime}
                                onChange={this.myChangeHandler}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="workDate">Date</label>
                            <input
                                name="workDate"
                                className="form-control"
                                type="date"
                                value={Moment(this.state.workDate).format("YYYY-MM-DD")}
                                onChange={this.myChangeHandler}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="service">Service</label>
                            <select name="service" className="form-control" onChange={this.myChangeHandler}>{
                                this.state.services.map((service, index) => <option key={index} value={service.serviceId} >{service.name}</option>)}
                            </select>
                        </div>
                        <button
                            type="submit"
                            className="btn btn-primary btn-block"
                            style={{
                                backgroundColor: "#341930",
                                border: "1px solid #341930",
                            }}
                        >
                            Add Working Hours
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}
