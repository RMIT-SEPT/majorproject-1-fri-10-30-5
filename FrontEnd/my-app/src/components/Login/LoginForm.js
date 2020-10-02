import React, { Component } from 'react'
import axios from "axios"
import '../../css/Form.css'

class LoginForm extends Component {

    constructor() {
        super();

        this.state = {
            //customerDetails: []
            uname:'',
            pw:''
        };
        this.myChangeHandler = this.myChangeHandler.bind(this);
    }

    mySubmitHandler = event => {
        event.preventDefault();
    
        const newUser = {
            // firstName: this.state.fname,
            // lastName:this.state.lname,
            // address:this.state.address,
            // phone:this.state.phone,
            username:this.state.uname,
            password:this.state.pw
        }
        console.log(newUser);
    
        // const baseurl = 'http://ec2-52-203-27-92.compute-1.amazonaws.com:8080'
        const baseurl = 'http://localhost:8080'
        axios.post(baseurl + '/api/user/login', 
            newUser
        )
        .then(res => //showOutput(res))
        {console.log(res);
        console.log(res.data);})
        .catch(err => console.error(err));
        
        // this.props.createNewUser(newUser, this.props.history);
    }

    myChangeHandler = event => {
        this.setState({[event.target.name]: event.target.value})
    };
    render() {
        //axios get to user
        return (
            <div className="forms-wrapper">
                <div className="forms-inner">
                    <h3>Login Form</h3>
                    <form id="login-form" onSubmit={this.mySubmitHandler}>
                        <div>
                            <label for="uname">Username </label>
                            <input name="uname" type='text' className="form-control" onChange={this.myChangeHandler} placeholder="Username" />
                        </div>
                        <div>
                            <label for="pw">Password </label>
                            <input name="pw" className="form-control" type='password' onChange={this.myChangeHandler} placeholder="Password" />
                        </div>
                        <p>User Type:</p>
                        <div className="custom-control custom-radio">
                            <input type="radio" className="custom-control-input" id="admin" name="userType" value="admin" />
                            <label className="custom-control-label" for="admin">Admin</label>
                        </div>
                        <div className="custom-control custom-radio">
                            <input type="radio" className="custom-control-input" id="worker" name="userType" value="emp" />
                            <label className="custom-control-label" for="worker">Worker</label>
                        </div>
                        <div className="custom-control custom-radio">
                            <input type="radio" className="custom-control-input" id="customer" name="userType" value="cust"/>
                            <label className="custom-control-label" for="customer">Customer</label>
                        </div>   
                        <br></br>                    
                        <button
                            type="submit"
                            className="btn btn-primary btn-block"
                            style={{
                                backgroundColor: "#341930",
                                border: "1px solid #341930",
                            }}>Log in
                        </button>
                    </form>
                </div>
            </div>
        )
    }
}

export default LoginForm;