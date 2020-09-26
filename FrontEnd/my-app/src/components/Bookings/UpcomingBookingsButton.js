import React from 'react'
import {Link} from "react-router-dom";

 const UpcomingBookingsButton=() => {
    return (
        <React.Fragment>
        <Link to="/upcomingBookings"
        className="btn btn-lg btn-info">
        View Upcoming Bookings
        </Link>
        </React.Fragment>
    )
};
export default UpcomingBookingsButton;
