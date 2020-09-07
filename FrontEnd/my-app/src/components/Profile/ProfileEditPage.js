import React, { Component } from 'react'
import axios from "axios";
import '../../css/Form.css'
import PropTypes from 'prop-types';

class ProfileEditPage extends Component {

    constructor(props) {
        super(props)

        this.state = {
            uname: 'cust5',
            profile: {
            fname: '',
            lname:'',
            name:'',
            address:'',
            phone:'',
            userName:'',
            password:'',
            status:'',
            data: {}
            }
        }
      }
      
      componentDidMount(){
        // const authorization = "Some Name" + cookie.load('token').replace("JWT","")
          const uname = this.state.uname
          const url = 'http://localhost:8080/api/customer/' + uname
          axios.get(url, {
            
            // headers: { 'Authorization': authorization }
          })
          .then(res => {
            this.setState({
              profile: res.data
            })
          })
          .catch((error) => {
            console.log("error",error)
          })
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
    
        axios.put('http://localhost:8080/api/customer/update', 
            customer
          )
          .then(res => //showOutput(res))
          {console.log(res);
          console.log(res.data);})
          .catch(err => console.error(err));
    }
    
    myChangeHandler = event => {
        this.setState({profile: {[event.target.name]: event.target.value}})
    };

    render() {
        return (
            <div id="register_form">
                <h1>Update Details Form</h1>
                <form onSubmit={this.mySubmitHandler}>
                    <div className='form-group'>
                    <label htmlFor="name">Name</label>
                    <input name="name" type='text' onChange={this.myChangeHandler} placeholder={this.state.profile.name}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="address">Address</label>
                    <input name="address" type='text' onChange={this.myChangeHandler} placeholder={this.state.profile.address}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="phone">Phone</label>
                    <input name="phone" type='Phone' onChange={this.myChangeHandler} placeholder={this.state.profile.phone}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="uname">Username</label>
                    <input name="uname" type='text' onChange={this.myChangeHandler} placeholder={this.state.profile.userName}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="pw">Password</label>
                    <input name="pw" type='password' onChange={this.myChangeHandler} placeholder={this.state.profile.password}/>
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

export default ProfileEditPage;