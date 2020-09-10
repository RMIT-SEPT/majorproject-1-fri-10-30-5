import React, { Component } from 'react'

const bookings =[{"custID":"1", "empID":"1", "date":"21/01/19", "time":"1100 hrs", "serviceID":"2", "status": "Completed"},
    {"custID":"1", "empID":"2", "date":"18/12/19", "time":"900 hrs", "serviceID":"1", "status": "Cancelled"},
    {"custID":"1", "empID":"1", "date":"03/06/20", "time":"1500 hrs", "serviceID":"3", "status": "Completed"}];


export default class PastBookings extends Component {
    render() {
        return (
            <div>
            <h5 className="display-4 text-center">Past Bookings</h5>  
            {              
                bookings.map((booking) => {
                    return (
                        <ul> 
                            <li>
                                <p id = 'empID'><b>Employee ID: </b>{booking.empID}</p>
                                <p id='serviceID'><b>Service ID: </b>{booking.serviceID}</p>
                                <p id='date'><b>Date: </b>{booking.date}</p>
                                <p id='time'><b>Time:</b>{booking.time}</p>
                                <p id='status'><b>Status: </b>{booking.status}</p>
                            </li>
                            <br/>
                        </ul>
                    );
                })
            }
            </div>
        );
    }
}