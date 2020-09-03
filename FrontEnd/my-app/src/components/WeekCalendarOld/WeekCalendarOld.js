import React, { Component } from 'react'
import './WeekCalendar.css'
import moment from 'moment'

class WeekCalendar extends Component {

  getDayLabel(num) {

    return moment().add(num, 'days').format("dddd").substr(0,3);
  }

  getDate(num) {

    return moment().date() + num + "/" + moment().month();
  }

  convertTime(time) {

    return 0;
  }

  render() {

    /*
      Object returned by querying
      WorkerID + all shifts for the next 7 days
    */
    const res = {
      
      0: {
        startTime: "13:00",
        endTime: "14:00",
        date: "3/8/2020"
      },
      1: {
        startTime: "15:00",
        endTime: "16:00",
        date: "5/8/2020"
      },
      2: {
        startTime: "14:00",
        endTime: "15:00",
        date: "56/8/2020"
      }
    }

    console.log(res[1]);

    return (
      <div>
        <table>
          <tr>
            <th></th>
            <th>{ this.getDayLabel(0) + " " + this.getDate(0) }</th>
            <th>{ this.getDayLabel(1) + " " + this.getDate(1) } </th>
            <th>{ this.getDayLabel(2) + " " + this.getDate(2) } </th>
            <th>{ this.getDayLabel(3) + " " + this.getDate(3) } </th>
            <th>{ this.getDayLabel(4) + " " + this.getDate(4) } </th>
            <th>{ this.getDayLabel(5) + " " + this.getDate(5) } </th>
            <th>{ this.getDayLabel(6) + " " + this.getDate(6) } </th>
          </tr>
          <tr>
          <td>8 AM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>9 AM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>10 AM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>11 AM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>12 AM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>1 PM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>2 PM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>3 PM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>4 PM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>5 PM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>6 PM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>7 PM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <td>8 PM</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        </table>
      </div>
    )
  }
}

export default WeekCalendar