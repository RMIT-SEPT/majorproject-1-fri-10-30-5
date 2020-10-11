import PastBookingsItem from '../../components/BookingData/PastBookingsItem'
import axios from "axios";
import React, { Component } from 'react'
import "../../css/Table.css"
import { authenticate } from '../../actions/auth';


class PastBookings extends Component {
    constructor(props) {
        super(props);
        this.state = {
            bookings: null,
            user: authenticate()
        };
    }

    componentDidMount() {
        const uname = this.state.user.username;
        //change to /pastBooking later
        const url = 'http://localhost:8080/api/booking/pastBookings/list/' + uname;
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
                return  <table className = "table table-striped">
                    <thead className = "thead">
                        <tr>
                            <th scope = "col">Worker</th>
                            <th scope = "col">Date</th>
                            <th scope = "col">Time</th>
                            <th scope = "col">Status</th>
                        </tr> 
                    </thead>
                    <tbody>
                    {                     
                        this.state.bookings.map((result, index) => (
                            <PastBookingsItem key= {index} empId = {result.empID }
                            date = {result.bookingDate}
                            time = {result.bookingTime}
                            //no service for now
                            status = {result.bookingStatus}/>
                        ))
                    }
                    </tbody>
                </table>
            } else {
                return <h5>You have no past bookings.</h5>
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