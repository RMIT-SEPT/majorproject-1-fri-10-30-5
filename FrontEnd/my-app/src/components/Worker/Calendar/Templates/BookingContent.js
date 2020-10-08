import React, { Component } from 'react'
import axios from 'axios'
import moment from 'moment'

const createHistory = require("history").createBrowserHistory;

export default class BookingContent extends Component {

    onBook = e => {

        e.preventDefault()
    
        // validate here
      
        console.log("booking: ", this.props.booking);
        let history = createHistory();
    
        axios.post('http://localhost:8080/api/booking/add', 
          this.props.booking
        )
        .then(res => {
            console.log("data:", res.data);
        })
        .catch(err => {
            console.error("error: ", err)

            history.push("../bookingFailed");
        });
    
        if(this.props.userType === 'admin') {
            history.push("../admin/upcoming");
        }
        else {
            history.push("../bookingSuccess");
        }

        let pathUrl = window.location.href;
        window.location.href = pathUrl;  
      }

    render() {
        return (
            <div>
                <p>{this.props.booking["Subject"]}</p>
                <form onSubmit={this.onBook}>
                    <h1>Confirm Booking</h1>
                    <p>Service: {this.props.service}</p>
                    <p>Worker: {this.props.booking.empID}</p>
                    <p>Time: {moment(this.props.booking.bookingTime, "hhmm A").format("h:mm A")}</p>
                    <p>Date: {moment(this.props.booking.bookingDate, "YYYY-MM-DD").format("DD MMM YYYY")}</p>
                    <button type='submit'>Book now</button>
                </form>
            </div>
        )
    }
}
