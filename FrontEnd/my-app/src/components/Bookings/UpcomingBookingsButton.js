import React from 'react'
import {Link} from "react-router-dom";

 const UpcomingBookingsButton=() => {
    return (
        <React.Fragment>
        <Link to="/upcomingBookings"
        className="btn btn-lg btn-info" style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>
        View Upcoming Bookings
        </Link>
        </React.Fragment>
    )
};
export default UpcomingBookingsButton;
