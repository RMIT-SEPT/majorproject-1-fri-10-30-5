import React, { Component } from 'react'
import '../../../css/BookingLanding.css'
import checkmark from '../../../images/iconfinder_Tick_Mark_Dark_1398912.svg'
import { authenticate } from './../../../actions/auth'

export default class BookingSuccess extends Component {

    constructor() {
        super()

        this.state = {
            user: authenticate()
        }
    }

    render() {
        return (
            <div className = "page-wrapper">
            <div className = "page-inner">
                <h4>
                    <img src = {checkmark} className = "checkmark"></img>
                    Your booking reservation is complete!
                </h4>
                <p>You can now view this booking in your upcoming bookings <a href = "upcomingBookings">here</a> or go back to <a href = "/dashboard">Dashboard</a>.</p>
            </div>
            </div>


                
        )
    }
}
