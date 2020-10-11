import React, { Component } from 'react'
import { authenticate } from '../../actions/auth'
import BookingCalendar from './Calendar/Booking'

export default class WorkerPage extends Component {

  constructor() {
    super()

    this.state = {
      user: authenticate()
    }
  }

  render() {
    return (
      <div>
        <BookingCalendar empID={this.props.match.params.empId} />
      </div>
    )
  }
}
