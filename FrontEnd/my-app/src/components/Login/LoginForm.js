import React, { Component } from 'react'
import '../../css/Form.css'

class LoginForm extends Component {
    render() {
        return (
            <div>
                <h1>Login Form</h1>
                <div>
                    <form onSubmit={this.mySubmitHandler}>
                    <div>
                    <label for="uname">UserName: </label>
                    <input name="uname" type='text' onChange={this.myChangeHandler} />
                    </div>
                    <div>
                    <label for="pw">Password: </label>
                    <input name="pw" type='password' onChange={this.myChangeHandler} />
                    </div>
                    <p>User Type:</p>
                    <input type="radio" id="admin" name="userType" value="admin" />
                    <label for="admin">Admin</label><br></br>
                    <input type="radio" id="worker" name="userType" value="emp" />
                    <label for="worker">Worker</label><br></br>
                    <input type="radio" id="customer" name="userType" value="cust" />
                    <label for="customer">Customer</label>
                    <br></br>
                    <br></br>
                    <hr></hr>
                    <br></br>
                    <input type='submit'/>
                    </form>
                </div>
            </div>
        )
    }
}

export default LoginForm;