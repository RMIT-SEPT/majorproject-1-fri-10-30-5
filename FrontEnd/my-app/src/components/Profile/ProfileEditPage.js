import React, { Component } from 'react'
import axios from "axios";
import '../../css/Form.css'
import PropTypes from 'prop-types';

class ProfileEditPage extends Component {

    constructor(props) {
        super(props)

        this.state = {
            uname: 'cus5',
            profile: {
            firstName: '',
            lastName:'',
            name:'',
            address:'',
            phone:'',
            userName:'',
            password:'',
            status:'',
            data: {}
            }
        }
        this.myChangeHandler = this.myChangeHandler.bind(this);
        this.mySubmitHandler = this.mySubmitHandler.bind(this);
      }
      
      componentDidMount(){
        // const authorization = "Some Name" + cookie.load('token').replace("JWT","")
          const uname = this.state.uname
          const url = 'http://localhost:8080/api/customer/' + uname
          axios.get(url, {
            
            // headers: { 'Authorization': authorization }
          })
          .then(res => {
            // this.setState({
            //   profile: res.data
            // })
            this.setState({
              profile: Object.assign({}, this.state.profile, res.data),
            });
            console.log("profile",res.data)
          })
          .catch((error) => {
            console.log("error",error)
          })
      }

    mySubmitHandler = event => {
        event.preventDefault();
    
        const customer = {
            firstName: this.state.profile.firstName,
            lastName:this.state.profile.lastName,
            name: this.state.profile.firstName + " " + this.state.profile.lastName,
            address:this.state.profile.address,
            phone:this.state.profile.phone,
            userName:this.state.profile.userName,
            password:this.state.profile.password
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
      this.setState({
        profile: Object.assign({}, this.state.profile, {
        [event.target.name]: event.target.value,
        }),
      });
      // this.setState({profile: {[event.target.name]: event.target.value}})
      // console.log("event", event)
      // console.log("profile", this.state.profile)
    };

    render() {
        return (
            <div id="register_form">
                <h1>Update Details Form</h1>
                <form onSubmit={this.mySubmitHandler}>
                    <div className='form-group'>
                    <label htmlFor="firstName">First Name</label>
                    <input name="firstName" type='text' onChange={this.myChangeHandler} placeholder={this.state.profile.firstName}
                    value={this.state.profile.firstName}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="lastName">Last Name</label>
                    <input name="lastName" type='text' onChange={this.myChangeHandler} placeholder={this.state.profile.lastName}
                    value={this.state.profile.lastName}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="address">Address</label>
                    <input name="address" type='text' onChange={this.myChangeHandler} placeholder={this.state.profile.address}
                    value={this.state.profile.address}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="phone">Phone</label>
                    <input name="phone" type='Phone' onChange={this.myChangeHandler} placeholder={this.state.profile.phone}
                    value={this.state.profile.phone}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="userName">Username</label>
                    <input name="userName" type='text' onChange={this.myChangeHandler} placeholder={this.state.profile.userName}
                    value={this.state.profile.userName}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="pw">Password</label>
                    <input name="pw" type='password' onChange={this.myChangeHandler} placeholder={this.state.profile.password}
                    value={this.state.profile.password}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="pwConfirm">Confirm Password</label>
                    <input name="pwConfirm" type='password' onChange={this.myChangeHandler} value={this.state.profile.password}/>
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