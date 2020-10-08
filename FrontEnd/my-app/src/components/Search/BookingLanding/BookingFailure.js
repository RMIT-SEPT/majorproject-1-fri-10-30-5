import React, { Component } from 'react'
import '../../../css/BookingLanding.css'
import checkmark from '../../../images/Flat_cross_icon.svg'

export default class BookingSuccess extends Component {
    render() {
        return (
            <div className = "page-wrapper">
            <div className = "page-inner">
                <h4>
                    <img src = {checkmark} className = "checkmark"></img>
                    Your booking reservation was unsuccessful.
                </h4>
                <p>Try booking again  <a href = "search">here</a> or go back to <a href = "/dashboard">Dashboard</a>.</p>
            </div>
            </div>


                
        )
    }
}
