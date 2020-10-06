import React, { Component } from 'react'
import WorkerCalendar from './WorkerCalendar/WorkerCalendar'

export default class WorkerPage extends Component {

  render() {
    return (
      <div>
        <WorkerCalendar empID={this.props.match.params.empId} />
      </div>
    )
  }
}
