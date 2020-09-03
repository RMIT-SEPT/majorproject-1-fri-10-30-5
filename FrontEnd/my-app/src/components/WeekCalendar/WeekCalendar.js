import React, { Component } from 'react'
import './WeekCalendar.css'
import DataTable from 'react-data-table-component';
import moment from 'moment'
 
/*
  Object returned by querying
  WorkerID + all shifts for the next 7 days
*/
const res = [
  {
    startTime: "13:00",
    endTime: "14:00",
    date: "3/8/2020"
  },
  {
    startTime: "15:00",
    endTime: "16:00",
    date: "5/8/2020"
  },
  {
    startTime: "14:00",
    endTime: "15:00",
    date: "6/8/2020"
  }
];

const data = [];
function convertData(data) {

  for(let i = 0; i < data.length; i++) {
    console.log(data[i]);

    // data.push({
    //   i: "hello"
    // },);
  }
};

// Convert the database result into an interpretable 
// object for the data table
convertData(res);

class WeekCalendar extends Component {
  render() {

    return (
      <DataTable
        title="This Week"
        columns={columns}
        data={data}
      />
    )
  }
};

// Columns in the table
// Each column is the date of the next seven days + current day
const columns = [
  {
    name: "Today",
    selector: String(moment().date() + 0 + "/" + moment().month()),
    sortable: false,
  },
];

for(let i = 1; i < 8; i++) {
  columns.push({
    name: moment().date() + i + "/" + moment().month(),
    selector: String(moment().date() + i + "/" + moment().month()),
    sortable: false,
  },);
}

export default WeekCalendar