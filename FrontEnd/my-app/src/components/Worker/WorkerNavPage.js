import React, { Component } from 'react'
import AddWorkHoursButton from './AddWorkHours/AddWorkHoursButton';

export default class WorkerNavPage extends Component {
    render() {
        return (
            <div className="worker-page">
                <h1 className="display-4 text-center">Worker</h1>
                <br />
                <AddWorkHoursButton />
            </div>
        )
    }
}
