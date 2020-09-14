import React, { Component } from 'react';
import axios from "axios";

export default class AddEmployee extends Component {
    constructor(props) {
        super(props);
        const admin = this.props.user.username;

        this.state = {
            admin: admin,
            firstName: '',
            lastName:'',
            address:'',
            phone:'',
            userName:'',
            password:''
        };
    }

    myChangeHandler = event => {
        this.setState({[event.target.name]: event.target.value})
    };

    mySubmitHandler = event => {
        event.preventDefault();
    
        const employee = {
            name: this.state.firstName + " " + this.state.lastName,
            address:this.state.address,
            phone:this.state.phone,
            userName:this.state.userName,
            password:this.state.password,
            admin: this.state.admin
        }
        console.log(employee);
    
        axios.post('http://localhost:8080/api/employee/add', 
            employee
          )
          .then(res => //showOutput(res))
          {console.log(res);
          console.log(res.data);})
          .catch(err => console.error(err));
    }
    
    render() {
        // admin validation

        return (
            <div id="addEmployeeForm">
                <h1>Add New Employee</h1>
                <form onSubmit={this.mySubmitHandler}>
                    <div className='form-group'>
                    <label htmlFor="firstName">First Name</label>
                    <input name="firstName" type='text' onChange={this.myChangeHandler} placeholder={this.state.firstName}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="lastName">Last Name</label>
                    <input name="lastName" type='text' onChange={this.myChangeHandler} placeholder={this.state.lastName}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="userName">Username</label>
                    <input name="userName" type='text' onChange={this.myChangeHandler} placeholder={this.state.userName}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="address">Address</label>
                    <input name="address" type='text' onChange={this.myChangeHandler} placeholder={this.state.address}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="phone">Phone</label>
                    <input name="phone" type='Phone' onChange={this.myChangeHandler} placeholder={this.state.phone}/>
                    </div>
                    <br></br>

                    <div className='form-group'>
                    <label htmlFor="pw">Password</label>
                    <input name="pw" type='password' onChange={this.myChangeHandler} placeholder={this.state.password}/>
                    </div>
                    <br></br>
                    <br></br>
                    <hr></hr>
                    <br></br>
                    <input type='submit'/>
                </form>
            </div>
        )
    }
}