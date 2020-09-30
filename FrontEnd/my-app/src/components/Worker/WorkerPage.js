import React, { Component } from 'react'
import WeekCalendar from '../WeekCalendar/WeekCalendar'
import WorkerCalendar from '../WeekCalendar/WorkerCalendar'

export default class WorkerPage extends Component {
  render() {
    return (
      <div>
        <WorkerCalendar />
      </div>
    )
  }
}
