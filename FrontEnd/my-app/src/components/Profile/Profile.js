import React, { Component } from 'react'

const custDetails = [{"userName":"jsmith", "password" : "123456", "name" : "John Smith", 
    "address" : "22 Bay St.", "phone" : "88881", "created_At": "25/08/20", "updated_At" : "null"}]; 

class Profile extends Component {
    render() {
        return (
            <div>
            <h5 className="display-4 text-center">Profile</h5>                
                {
                    custDetails.map((c) => {
                        return (
                            <table>                              
                                <tr>
                                    <th>Username:</th>
                                    <td>{c.userName}</td>
                                </tr>
                                <tr>
                                    <th>Name:</th>
                                    <td>{c.name}</td>
                                </tr>
                                <tr>
                                    <th>Address:</th>
                                    <td>{c.address}</td>
                                </tr>
                                <tr>
                                    <th>Phone:</th>
                                    <td>{c.userName}</td>
                                </tr>
                          </table>

                        );
                    })
                }
            </div>
        );
    }
}

export default Profile;
