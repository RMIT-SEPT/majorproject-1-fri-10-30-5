import React, { PropTypes, Component } from 'react'
import axios from "axios";
import '../../css/Form.css'
import classnames from "classnames"

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
            uType: '',
            pw:''
        };
        this.myChangeHandler = this.myChangeHandler.bind(this);
    }

    componentWillReceiveProps   (nextProps){
        if(nextProps.errors){
            this.setState({
                erros: nextProps.errors
            });
        }
    }

     mySubmitHandler = event => {
        event.preventDefault();
    
        const newUser = {
            // firstName: this.state.fname,
            // lastName:this.state.lname,
            // address:this.state.address,
            // phone:this.state.phone,
            userName:this.state.uname,
            userType:"admin",
            // userType:this.state.uType,
            password:this.state.pw,
            confirmPassword:this.state.pw
        }
        console.log(newUser);
    
        // const baseurl = 'http://ec2-52-203-27-92.compute-1.amazonaws.com:8080'
        const baseurl = 'http://localhost:8080'
        axios.post(baseurl + '/api/user/register', 
            newUser
        )
        .then(res => //showOutput(res))
        {console.log(res);
        console.log(res.data);})
        .catch(err => console.error(err));
        
        // this.props.createNewUser(newUser, this.props.history);
    }

    myChangeHandler = event => {
        this.setState({ [event.target.name]: event.target.value })
    };

    render() {
        return (
            <div className="forms-wrapper">
                <div className="forms-inner">
                    <form id="register_form" onSubmit={this.mySubmitHandler}>
                        <h3>Sign Up</h3>
                            <div className='form-group'>
                                <label htmlFor="fname">First name</label>
                                <input name="fname" className="form-control" type='text' onChange={this.myChangeHandler} value={this.state.fname} />
                            </div>
                            <div className='form-group'>
                                <label htmlFor="lname">Last name</label>
                                <input name="lname" className="form-control" type='text' onChange={this.myChangeHandler} value={this.state.lname} />
                            </div>
                            <div className='form-group'>
                                <label htmlFor="address">Address</label>
                                <input name="address" className="form-control" type='text' onChange={this.myChangeHandler} value={this.state.address} />
                            </div>
                            <div className='form-group'>
                                <label htmlFor="phone">Phone</label>
                                <input name="phone" className="form-control" type='Phone' onChange={this.myChangeHandler} value={this.state.phone} />
                            </div>
                            <div className='form-group'>
                                <label htmlFor="uname">Username</label>
                                <input name="uname" className="form-control" type='text' onChange={this.myChangeHandler} value={this.state.uname} />
                            </div>
                            <div className='form-group'>
                                <label htmlFor="pw">Password</label>
                                <input name="pw" className="form-control" type='password' onChange={this.myChangeHandler} value={this.state.pw} />
                            </div>
                            <div className='form-group'>
                                <label htmlFor="pwConfirm">Confirm Password</label>
                                <input name="pwConfirm" className="form-control" type='password' onChange={this.myChangeHandler} />
                            </div>
                            <button
                            type="submit"
                            className="btn btn-primary btn-block"
                            style={{
                              backgroundColor: "#341930",
                              border: "1px solid #341930",
                            }}
                          >
                            Sign Up
                          </button>
                          <p className="forgot-password text-right">
                            Already registered <a href="/login">sign in?</a>
                            </p>
                        </form>
                </div>
            </div>
        );
    }
}

export default RegisterForm;