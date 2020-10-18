import React, { Component } from 'react'
import '../../../css/BookingLanding.css'
import checkmark from '../../../images/iconfinder_Tick_Mark_Dark_1398912.svg'
import { authenticate } from './../../../actions/auth'

export default class WorkerSuccess extends Component {

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
                    Worker has been added!
                </h4>
                <p>You can go back to the admin panel to add another worker <a href = "/admin">here</a>.</p>
            </div>
            </div>


                
        )
    }
}
