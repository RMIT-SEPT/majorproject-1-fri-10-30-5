import React, { Component } from 'react'
import '../../css/Form.css'

class LoginForm extends Component {
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