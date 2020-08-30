import React, { Component } from 'react'

const bookings =[{"custID":"1", "empID":"3", "date":"21/10/20", "time":"1300 hrs", "serviceID":"1", "status": "Booked"},
{"custID":"1", "empID":"3", "date":"30/10/20", "time":"1500 hrs", "serviceID":"2", "status": "Cancelled"}];



export default class UpcomingBookings extends Component {
    render() {
        return (
            <div>
            <h5 className="display-4 text-center">Upcoming Bookings</h5>  
            {              
                bookings.map((booking) => {
                    return (
                        <table>                              
                            <tr>
                                <th>Employee ID:</th>
                                    <td>{booking.empID}</td>                                                         
                             </tr>
                            <tr>
                                <th>Date:</th>
                                <td>{booking.date}</td>
                            </tr>
                            <tr>
                                <th>Time:</th>
                                <td>{booking.time}</td>
 
                            </tr>
                            <tr>
                                <th>Service ID:</th>
                                <td>{booking.serviceID}</td>
     
                            </tr>
                            <tr>
                                <th>Satus:</th>
                                <td>{booking.status}</td>
                            </tr>
                            <hr/>
                      </table>
                    );
                })
            }
            </div>
        );
    }
}
