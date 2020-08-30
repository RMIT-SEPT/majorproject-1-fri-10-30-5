import React, { Component } from 'react'
import '../../css/Form.css'

class RegisterForm extends Component {
    render() {
        return (
            <div>
            <h1>Register New Customer Form</h1>
            <form onSubmit={this.mySubmitHandler}>
            <div>
            <label for="fname">First Name:</label>
            <input name="fname" type='text' onChange={this.myChangeHandler} />
            </div>
            <br></br>
            <div>
            <label for="lname">Last Name</label>
            <input name="lname" type='text' onChange={this.myChangeHandler} />
            </div>
            <br></br>
            <div>
            <label for="address">Address</label>
            <input name="address" type='text' onChange={this.myChangeHandler} />
            </div>
            <br></br>
            <div>
            <label for="phone">Phone</label>
            <input name="phone" type='Phone' onChange={this.myChangeHandler} />
            </div>
            <br></br>
            <div>
            <label for="uname">Username</label>
            <input name="uname" type='text' onChange={this.myChangeHandler} />
            </div>
            <br></br>
            <div>
            <label for="pw">Password</label>
            <input name="pw" type='password' onChange={this.myChangeHandler} />
            </div>
            <br></br>
            <div>
            <label for="pwConfirm">Confirm Password</label>
            <input name="pwConfirm" type='password' onChange={this.myChangeHandler} />
            </div>
            <br></br>
            <br></br>
            <hr></hr>
            <br></br>
            <input type='submit'/>
            </form>
            </div>
        );
    }
}

export default RegisterForm;