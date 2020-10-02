import React, { Component } from 'react'
import axios from 'axios'

const createHistory = require("history").createBrowserHistory;

export default class BookingContent extends Component {

    onBook = e => {

        e.preventDefault()
    
        // validate here
      
        console.log("booking: ", this.props.booking);
    
        axios.post('http://localhost:8080/api/booking/add', 
          this.props.booking
        )
        .then(res => {
            console.log("data:", res.data);
        })
        .catch(err => console.error("error: ", err));
    
        let history = createHistory();
        history.push("../upcomingBookings");
        let pathUrl = window.location.href;
        window.location.href = pathUrl;  
      }

    render() {
        return (
            <div>
                <p>{this.props.booking["Subject"]}</p>
                <form onSubmit={this.onBook}>
                    <button type='submit'>Book now</button>
                </form>
            </div>
        )
    }
}
