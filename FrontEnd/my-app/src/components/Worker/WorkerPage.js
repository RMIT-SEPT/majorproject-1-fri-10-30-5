import React, { Component } from 'react'
import BookingCalendar from './Calendar/Booking'

export default class WorkerPage extends Component {
  render() {
    return (
      <div>
        <BookingCalendar empID={this.props.match.params.empId} />
      </div>
    )
  }
}
