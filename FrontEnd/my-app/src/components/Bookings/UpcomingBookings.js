import React, { Component } from 'react'
import axios from 'axios'

export default class UpcomingBookings extends Component {

    constructor() {
        super();

        this.state = {
            results: []
        }

        this.getBookings();
    }

    getBookings = e => {
        const custId = 'cus5'
        const url = 'http://localhost:8080/api/booking/upcomingBookings/list/' + custId
        this.setState({ loading: true });
        axios.get(url, {
        // headers: { 'Authorization': authorization }
        })
        .then(res => {
            
            this.setState({
            results: res.data,
            loading: false,
            searched: true
            }, () => {
                console.log("Got bookings:", this.state.results)
                this.forceUpdate()
            })
            
        })
        .catch((error) => {
            console.log("error",error)
        })

    }

    render() {
        
        return (
            <div>
            <h5 className="display-4 text-center">Upcoming Bookings</h5>  
            {              
                this.state.results.map((booking, index) => (
                    <ul> 
                        <li key={index}>
                            <p id = 'empID'><b>Employee ID: </b>{booking['empID']}</p>
                            <p id='serviceID'><b>Service ID: </b>{booking['id']}</p>
                            <p id='date'><b>Date: </b>{booking['bookingDate']}</p>
                            <p id='time'><b>Time:</b>{booking['bookingTime']}</p>
                            <p id='status'><b>Status: </b>{booking['bookingStatus']}</p>
                        </li>
                        <br/>
                    </ul>
                ))
            }
            </div>
        );
    }
}
