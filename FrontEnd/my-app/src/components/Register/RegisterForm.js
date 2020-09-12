import React, { Component } from 'react'
import axios from "axios";
import '../../css/Form.css'

class RegisterForm extends Component {

    constructor() {
        super();

        this.state = {
            //customerDetails: []
            fname: '',
            lname:'',
            address:'',
            phone:'',
            uname:'',
            pw:''
        };
    }

    mySubmitHandler = event => {
        event.preventDefault();
    
        const customer = {
            fname: this.state.fname,
            lname:this.state.lname,
            name: this.state.fname + " " + this.state.lname,
            address:this.state.address,
            phone:this.state.phone,
            userName:this.state.uname,
            password:this.state.pw
        }
        console.log(customer);
    
        axios.post('http://localhost:8080/api/customer/add', 
            customer
          )
          .then(res => //showOutput(res))
          {console.log(res);
          console.log(res.data);})
          .catch(err => console.error(err));
    }
    
    myChangeHandler = event => {
        this.setState({[event.target.name]: event.target.value})
    };

    render() {
        return (
            <div id="register_form">
                <h1>Register New Customer Form</h1>
                <form onSubmit={this.mySubmitHandler}>
                    <div className='form-group'>
                    <label htmlFor="fname">First Name:</label>
                    <input name="fname" type='text' onChange={this.myChangeHandler} value={this.state.fname}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="lname">Last Name</label>
                    <input name="lname" type='text' onChange={this.myChangeHandler} value={this.state.lname}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="address">Address</label>
                    <input name="address" type='text' onChange={this.myChangeHandler} value={this.state.address}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="phone">Phone</label>
                    <input name="phone" type='Phone' onChange={this.myChangeHandler} value={this.state.phone}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="uname">Username</label>
                    <input name="uname" type='text' onChange={this.myChangeHandler} value={this.state.uname}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="pw">Password</label>
                    <input name="pw" type='password' onChange={this.myChangeHandler} value={this.state.pw}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="pwConfirm">Confirm Password</label>
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