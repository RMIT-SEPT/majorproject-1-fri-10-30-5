import React, { Component } from 'react'
import '../../css/WeekCalendar.css'
import DataTable from 'react-data-table-component';
import moment from 'moment'
import axios from 'axios'

class WeekCalendar extends Component {

  constructor() {
    super();

    this.state = {
      
      // EXAMPLE Object returned by querying
      // WorkerID + all shifts for the next 7 days
      res: [{
        startTime: "13:00",
        endTime: "14:00",
        date: "9/18/2020"
      },
      {
        startTime: "15:00",
        endTime: "16:00",
        date: "9/21/2020"
      },
      {
        startTime: "14:00",
        endTime: "15:00",
        date: "9/23/2020"
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

  onSubmit = e => {
    e.preventDefault();

    // need to validate !!!!!!!!
    
    const booking = {
      custID: 1,
      empID: 1,
      bookingTime: this.state.time,
      bookingDate: this.state.date,
      created_at: moment().format("yyyy-MM-DD"),
    }

    console.log(booking);

    axios.put('http://localhost:8080/api/booking/add', 
            booking
          )
          .then(res => //showOutput(res))
          {console.log(res);
          console.log(res.data);})
          .catch(err => console.error(err));

    // redirect to upcoming bookings page or dashboard here
  }

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

    const handleSelection = (state) => {

      let booking, date;

      if(state.selectedRows[0] !== undefined) {
        date = Object.keys(state.selectedRows[0]);
     

        this.setState({
          rows: state.selectedRows,
          time: moment(state.selectedRows[0][date], "hh:mm A").format("HHmm"),
          date: moment().format('yyyy/') + date
        });
      }
    }

    for(let i = 1; i < 8; i++) {
      columns.push({
        name: moment().date() + i + "/" + (moment().month()+1),
        selector: String(moment().date() + i + "/" + (moment().month()+1)),
        sortable: false,
      },);
    }

    return (
      <div>
        <DataTable
          title="This Week"
          columns={columns}
          data={this.state.data}
          selectableRows
          selectableRowsNoSelectAll
          selectableRowsHighlight
          onSelectedRowsChange={handleSelection}
        />

        <form>
          <button type='submit' onSubmit={this.onSubmit}>Book Now</button>
        </form>
      </div>
    )
  }
};

export default WeekCalendar