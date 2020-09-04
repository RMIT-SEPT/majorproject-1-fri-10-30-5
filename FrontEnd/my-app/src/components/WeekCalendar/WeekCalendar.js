import React, { Component } from 'react'
import './WeekCalendar.css'
import DataTable from 'react-data-table-component';
import moment from 'moment'

class WeekCalendar extends Component {

  constructor() {
    super();

    this.state = {
      
      // Object returned by querying
      // WorkerID + all shifts for the next 7 days
      res: [{
        startTime: "13:00",
        endTime: "14:00",
        date: "9/9/2020"
      },
      {
        startTime: "15:00",
        endTime: "16:00",
        date: "9/5/2020"
      },
      {
        startTime: "14:00",
        endTime: "15:00",
        date: "9/11/2020"
      }]
    };

    this.convertData(this.state.res)
  }

  // Convert the database result into an 
  // interpretable object for the data table
  convertData(dataSet) {

    let newData = [];
  
    for(let i = 0; i < dataSet.length; i++) {
  
      let tmp = moment(dataSet[i]["date"]).format("D/M/YYYY").substr(0,2);
  
      if(parseInt(tmp) > 10) {
        tmp = moment(dataSet[i]["date"]).format("D/M/YYYY").substr(0,4);
      }
      else {
        tmp = moment(dataSet[i]["date"]).format("D/M/YYYY").substr(0,3);
      }
  
      newData.push({[tmp]: moment(dataSet[i]["startTime"], "HH:mm").format("h:mm A")});
    }

    this.state.data = newData
  };

  render() {

    // Columns in the table
    // Each column is the date of the next seven days + current day
    const columns = [
      {
        name: "Today",
        selector: String(moment().date() + "/" + (moment().month()+1)),
        sortable: false,
      },
    ];

    for(let i = 1; i < 8; i++) {
      columns.push({
        name: moment().date() + i + "/" + (moment().month()+1),
        selector: String(moment().date() + i + "/" + (moment().month()+1)),
        sortable: false,
      },);
    }

    return (
      <DataTable
        title="This Week"
        columns={columns}
        data={this.state.data}
      />
    )
  }
};

export default WeekCalendar