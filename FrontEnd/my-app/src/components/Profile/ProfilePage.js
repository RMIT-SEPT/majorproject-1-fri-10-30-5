import React, { Component } from 'react'
import axios from "axios";
import Profile from './Profile'
import { authenticate } from '../../actions/auth';

class ProfilePage extends Component {

    constructor(props) {
        super(props)

        this.state = {
            uname:'cus5',
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
            },

            user: authenticate()
        }
      }

      componentDidMount(){
        // const authorization = "Some Name" + cookie.load('token').replace("JWT","")
          // var id = props.match.params.id;
          const uname = this.state.user.username;
          const uType = this.state.user.userType
          const url = 'http://localhost:8080/api/person/' + uType + '/' + uname
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

    render() {
        return (
            <div id="profile_page">
            {
                    <Profile
                    username={this.state.profile.userName}
                    fname={this.state.profile.firstName}
                    lname={this.state.profile.lastName}
                    address={this.state.profile.address}
                    phone={this.state.profile.phone}
                    />
            }
            </div>            
        );
    }
}

export default ProfilePage;