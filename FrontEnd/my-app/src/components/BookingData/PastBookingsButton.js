import React from 'react'
import {Link} from "react-router-dom";

 const PastBookingsButton=() => {
    return (
        <React.Fragment>
        <Link to="/pastBookings"
        className="btn btn-lg btn-info" style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>
        View Past Bookings
        </Link>
        </React.Fragment>
    )
};
export default PastBookingsButton;