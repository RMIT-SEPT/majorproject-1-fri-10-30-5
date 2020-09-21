import React, { Component } from 'react'
import '../../css/WeekCalendar.css'
import DataTable from 'react-data-table-component';
import moment from 'moment'
import axios from 'axios'

class WeekCalendar extends Component {

  constructor(props) {
    super(props);

    this.state = {
      time:'',
      date:'',
      rows:'',
      selectedRows: [],
      toggledClearRows: false,
      data: [],
      empId:this.props.empID,
      workingHours: [],
    };

    // this.setState({empId: window.location.pathname.substr(1, window.location.pathname.length-14)})
  }

  componentDidMount(){
    // const authorization = "Some Name" + cookie.load('token').replace("JWT","")
      // var id = props.match.params.id;
      // const empId = {this.props.name}
      // const empId = window.location.pathname.substr(1, window.location.pathname.length-14)
      const empId = this.state.empId;
      const url = 'http://localhost:8080/api/workinghours/list/' + empId
      axios.get(url, {
        // headers: { 'Authorization': authorization }
      })
      .then(res => {
        this.setState({
          workingHours: res.data
        }, () => {
          console.log(this.state.workingHours)
          this.convertData(this.state.workingHours)
        })
      })
      .catch((error) => {
        console.log("error",error)
      })
      this.setState({empId: empId})
  }

  // Convert the database result into an 
  // interpretable object for the data table
  convertData(dataSet) {
    let newData = [];

    for(let i = 0; i < dataSet.length; i++) {
  
      let day = moment(dataSet[i]["workDate"]).format("D/M/YYYY").substr(0,2);
      let date;
      // console.log(day)
  
      if(parseInt(day) > 10) {
        date = moment(dataSet[i]["workDate"]).format("D/M/YYYY").substr(0,4);
      }
      else {
        date = moment(dataSet[i]["workDate"]).format("D/M/YYYY").substr(0,3);
      }
  
      newData.push({[date]: moment(dataSet[i]["startTime"], "HH:mm").format("h:mm A"),
    service: dataSet[i]["service"]});
      
    }
    this.setState({data: newData})
    console.log(newData)
  };

  onSubmit = e => {
    e.preventDefault();

    // need to validate !!!!!!!!
    
    const booking = {
      custID: 1,
      empID: this.state.empId,
      bookingTime: this.state.time,
      bookingDate: this.state.date,
    }

    // console.log("booking: ", booking);

    axios.post('http://localhost:8080/api/booking/add', 
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

    const onClick = (e) => {

      // e.preventDefault();
      console.log("event", e)
      if (e.selectedCount <= 1){
        this.setState({ toggledClearRows: false })
        handleSelection(this.state)
      }else{
        this.setState({ toggledClearRows: true }); 
        // alert("Unselect previous booking before selecting another time")
      }
      
    }

    return (
      <div>
      <h1 id="empID" style={{display:'none'}}>{this.props.empID}</h1>
        <DataTable
          title="This Week"
          columns={columns}
          data={this.state.data}
          selectableRows
          selectableRowsNoSelectAll
          selectableRowsHighlight
          onSelectedRowsChange={onClick}
          clearSelectedRows={this.state.toggledClearRows}
        />

        <form onSubmit={this.onSubmit}>
          <button type='submit' >Book Now</button>
        </form>
      </div>
    )
  }
};

export default WeekCalendar