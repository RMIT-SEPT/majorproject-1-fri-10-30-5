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