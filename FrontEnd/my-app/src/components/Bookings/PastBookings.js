import PastBookingsItem from '../../components/Bookings/PastBookingsItem'
import axios from "axios";
import React, { Component } from 'react'


class PastBookings extends Component {
    constructor(props) {
        super(props);
        this.state = {
            bookings: null
        };
    }

    componentDidMount() {
        const uname = this.props.user.username;
        //change to /pastBooking later
        const url = 'http://localhost:8080/api/booking/list/' + uname;
        axios.get(url, {
            // headers: { 'Authorization': authorization }
          })
          .then(res => {
            this.setState({
              bookings: res.data
            })
          })
          .catch((error) => {
            console.log("error",error)
          })    
    }

 
    
    render() {
        let hasBookings = this.state.bookings;
        const results = () => {
            if(hasBookings !== null) {
                return  <table className = "table">
                    <thead>
                        <tr>
                            <th>Worker</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Status</th>
                        </tr> 
                    </thead>
                    <tbody>
                    {                     
                        this.state.bookings.map((result) => (
                            <PastBookingsItem empId = {result.empID}
                            date = {result.bookingDate}
                            time = {result.bookingTime}
                            //no service for now
                            status = {result.bookingStatus}/>
                        ))
                    }
                    </tbody>
                </table>
            } else {
                return <h1>You have no past bookings</h1>
            }
        
        }
        return (
                <div>
                <h5 className="display-4 text-center">Past Bookings</h5> 
                    {results()}
                </div>
        );
    }
}

export default PastBookings;