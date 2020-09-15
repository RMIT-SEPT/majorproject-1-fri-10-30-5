import React, { Component } from 'react'
import PastBookingsButton from './Bookings/PastBookingsButton';
import UpcomingBookingsButton from './Bookings/UpcomingBookingsButton';
import SearchPageButton from './SearchAvailability/SearchPageButton';
import AddEmployeeButton from './AddEmployee/AddEmployeeButton';
import {link, Link} from "react-router-dom";

class Dashboard extends Component {
    render() {
        var button;
        if(this.props.user.userType === 'admin') {
            button = <AddEmployeeButton />;
        }
        return (
            <div className="Dashboard">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Dashboard</h1>
                        <br />
                        <Link to={{
                          pathname: `/profile/${this.props.user.username}` 
                        }} 
                          className="btn btn-lg btn-info" >
                        Profile
                       </Link>
                        <br />
                        <br />
                        <UpcomingBookingsButton />
                        <br />
                        <br />
                        <PastBookingsButton />
                        <br />
                        <br />
                        <SearchPageButton />
                        <br />
                        <br />
                        {button}
                        <br />              
                    </div>
                </div>
            </div>
        </div>
    
        )
    }
}
export default Dashboard;