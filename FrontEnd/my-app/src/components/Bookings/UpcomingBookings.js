import React, { Component } from 'react'

const bookings =[{"custID":"1", "empID":"3", "date":"21/10/20", "time":"1300 hrs", "serviceID":"1", "status": "Booked"},
{"custID":"1", "empID":"3", "date":"30/10/20", "time":"1500 hrs", "serviceID":"2", "status": "Cancelled"}];



export default class UpcomingBookings extends Component {
    render() {
        return (
            <div>
            <h5 className="display-4 text-center">Upcoming Bookings</h5>  
            {              
                bookings.map((bookings) => {
                    return (
                        <ul> 
                            <li>
                                <p id = 'empID'><b>Employee ID: </b>{bookings.empID}</p>
                                <p id='serviceID'><b>Service ID: </b>{bookings.serviceID}</p>
                                <p id='date'><b>Date: </b>{bookings.date}</p>
                                <p id='time'><b>Time:</b>{bookings.time}</p>
                                <p id='status'><b>Status: </b>{bookings.status}</p>
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
