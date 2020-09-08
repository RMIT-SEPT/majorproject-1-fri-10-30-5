import React, { Component } from 'react'
import axios from "axios";
import Profile from './Profile'

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
                    <Profile
                    username={this.state.profile.userName}
                    name={this.state.profile.name}
                    address={this.state.profile.address}
                    phone={this.state.profile.phone}
                    />
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