import React from 'react'
import {link, Link} from "react-router-dom";

 const PastBookingsButton=() => {
    return (
        <React.Fragment>
        <Link to="/admin/past"
        className="btn btn-lg btn-info" style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>
        View All Past Bookings
        </Link>
        </React.Fragment>
    )
};
export default PastBookingsButton;
