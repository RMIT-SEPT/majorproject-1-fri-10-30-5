import React from 'react'
import {link, Link} from "react-router-dom";

 const UpcomingBookingsButton=() => {
    return (
        <React.Fragment>
        <Link to="/admin/upcoming"
        className="btn btn-lg btn-info" style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>
        View All Upcoming Bookings
        </Link>
        </React.Fragment>
    )
};
export default UpcomingBookingsButton;
