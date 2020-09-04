import React, { Component } from 'react'
import './WeekCalendar.css'
import DataTable from 'react-data-table-component';
import moment from 'moment'

class WeekCalendar extends Component {

  constructor() {
    super();

    this.state = {
      data: ['test'],

      /*
      Object returned by querying
      WorkerID + all shifts for the next 7 days
      */
      res: [{
        startTime: "13:00",
        endTime: "14:00",
        date: "8/3/2020"
      },
      {
        startTime: "15:00",
        endTime: "16:00",
        date: "8/5/2020"
      },
      {
        startTime: "14:00",
        endTime: "15:00",
        date: "8/16/2020"
      }]
    };

    // Convert the database result into an interpretable 
    // object for the data table
    // console.log(this.state.res)
    this.convertData(this.state.res)
    
  
  }

  convertData(dataSet) {

    // console.log(dataSet)

    let newData = [];
  
    for(let i = 0; i < dataSet.length; i++) {
  
      let tmp = moment(dataSet[i]["date"]).format("D/M/YYYY").substr(0,2);
  
      if(parseInt(tmp) > 10) {
        tmp = moment(dataSet[i]["date"]).format("D/M/YYYY").substr(0,4);
      }
      else {
        tmp = moment(dataSet[i]["date"]).format("D/M/YYYY").substr(0,3);
      }
  
      newData.push(tmp);
    }
  
    // console.log(newData)
    this.setState({data: newData})
    
  };

  render() {

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

    console.log(this.state.data)

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