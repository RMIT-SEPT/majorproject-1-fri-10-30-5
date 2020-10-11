import React, { Component } from 'react'
import { authenticate } from '../../actions/auth'

export default class WorkerNavPage extends Component {

    constructor() {
        super()

        this.state = {
            user: authenticate()
        }
    }

    render() {
        return (
            <div className="worker-page">
                <h1 className="display-4 text-center">Worker</h1>
                <br />
                
            </div>
        )
    }
}
