import React, { Component } from 'react'
<<<<<<< HEAD:FrontEnd/my-app/src/components/Bookings/Booking.js
import BookingCalendar from '../Calendar/BookingCalendar'
=======
import WorkerCalendar from './WorkerCalendar/WorkerCalendar'
>>>>>>> registerUser:FrontEnd/my-app/src/components/SearchAvailability/WorkerBooking.js

export default class WorkerPage extends Component {

  render() {
    return (
      <div>
        <BookingCalendar empID={this.props.match.params.empId} />
      </div>
    )
  }
}
