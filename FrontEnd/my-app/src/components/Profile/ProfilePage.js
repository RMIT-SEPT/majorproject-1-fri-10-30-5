import React, { Component } from 'react'
import axios from "axios";
import PropTypes from 'prop-types';
import Profile from './Profile'

const custDetails = [{"userName":"jsmith", "password" : "123456", "name" : "John Smith", 
    "address" : "22 Bay St.", "phone" : "88881", "created_At": "25/08/20", "updated_At" : "null"}]; 

class ProfilePage extends Component {

    constructor(props) {
        super(props)

        this.state = {
            uname:'cust5',
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
          // var id = props.match.params.id;
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

    render() {
        return (
            <div id="profile_page">
            {
                // this.customer.map((c) => {
                // return(
                    <Profile
                    // username={c.userName}
                    // name={c.name}
                    // address={c.address}
                    // phone={c.phone}
                    username={this.state.profile.userName}
                    name={this.state.profile.name}
                    address={this.state.profile.address}
                    phone={this.state.profile.phone}
                    />
                // );
                // })
            }
            </div>            
        );
    }
}

ProfilePage.propTypes = {
    // dispatch: PropTypes.func.isRequired,
    // pageState: PropTypes.object.isRequired
};

export default ProfilePage;