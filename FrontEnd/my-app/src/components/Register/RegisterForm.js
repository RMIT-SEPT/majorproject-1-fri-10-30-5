import React, { PropTypes, Component, useCallBack, useCallback } from 'react'
import axios from "axios";
import '../../css/Form.css'

const addPerson = (props) => {
    const newPerson = {
        firstName: props.fname,
        lastName: props.lname,
        address: props.address,
        phone: props.phone,
        userName: props.uname,
        password: props.pw,
        userType: props.uType
    }
    console.log(newPerson);

    const baseurl = 'http://localhost:8080'
    axios.post(baseurl + '/api/person/add',
        newPerson
    )
        .then(res => //showOutput(res))
        {
            console.log(res);
            console.log(res.data);
        })
        .catch(err => console.error(err));
};

class RegisterForm extends Component {
    constructor() {
        super();

        this.state = {
            uname: '',
            uType: '',
            pw: '',
            fname: '',
            lname: '',
            address: '',
            phone: '',


        };
        this.myChangeHandler = this.myChangeHandler.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({
                erros: nextProps.errors
            });
        }
    }

    mySubmitHandler = event => {
        event.preventDefault();

        const newUser = {
            userName: this.state.uname,
            userType: this.state.uType,
            password: this.state.pw,
            confirmPassword: this.state.pw
        }
        console.log(newUser);

        // const baseurl = 'http://ec2-52-203-27-92.compute-1.amazonaws.com:8080'
        const baseurl = 'http://localhost:8080'
        axios.post(baseurl + '/api/user/register',
            newUser
        )
            .then(res => //showOutput(res))
            {
                addPerson(this.state);
                console.log(res);
                console.log(res.data);
            })
            .catch(err => console.error(err));

        // this.props.createNewUser(newUser, this.props.history);
    }

    myChangeHandler = event => {
        this.setState({ [event.target.name]: event.target.value })
    };

    render() {
        return (
            <div>
                <div className="forms-wrapper">
                <div className="forms-inner">
                    <form id="register_form" onSubmit={this.mySubmitHandler}>
                        <h3>Sign Up</h3>
                        <div className='form-group'>
                            <label htmlFor="userType">Account Type:</label>
                            <select name="uType" className="form-control" id="userType" value={this.state.uType} onChange={this.myChangeHandler} required>
                                <option value=""> </option>
                                <option value="customer">Customer</option>
                                <option value="employee">Employee</option>
                                <option value="admin">Admin</option>
                            </select>
                        </div>
                        <div className='form-group'>
                            <label htmlFor="fname">First name</label>
                            <input name="fname" className="form-control" type='text' onChange={this.myChangeHandler} value={this.state.fname} required />
                        </div>
                        <div className='form-group'>
                            <label htmlFor="lname">Last name</label>
                            <input name="lname" className="form-control" type='text' onChange={this.myChangeHandler} value={this.state.lname} required />
                        </div>
                        <div className='form-group'>
                            <label htmlFor="address">Address</label>
                            <input name="address" className="form-control" type='text' onChange={this.myChangeHandler} value={this.state.address} required />
                        </div>
                        <div className='form-group'>
                            <label htmlFor="phone">Phone</label>
                            <input name="phone" className="form-control" type='Phone' onChange={this.myChangeHandler} value={this.state.phone} required />
                        </div>
                        <div className='form-group'>
                            <label htmlFor="uname">Username</label>
                            <input name="uname" className="form-control" type='text' onChange={this.myChangeHandler} value={this.state.uname} required />
                        </div>
                        <div className='form-group'>
                            <label htmlFor="pw">Password</label>
                            <input name="pw" className="form-control" type='password' onChange={this.myChangeHandler} value={this.state.pw} required />
                        </div>
                        <div className='form-group'>
                            <label htmlFor="pwConfirm">Confirm Password</label>
                            <input name="pwConfirm" className="form-control" type='password' onChange={this.myChangeHandler} required />
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
            </div>
        );
    }
}

export default RegisterForm;