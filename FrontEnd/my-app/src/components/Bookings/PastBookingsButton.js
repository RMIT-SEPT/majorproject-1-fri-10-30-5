import React from 'react'
import {link, Link} from "react-router-dom";

 const PastBookingsButton=() => {
    return (
        <React.Fragment>
        <Link to="/pastBookings"
        className="btn btn-lg btn-info">
        View Past Bookings
        </Link>
        </React.Fragment>
    )
};
export default PastBookingsButton;