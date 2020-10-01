import React from 'react'
import {link, Link} from "react-router-dom";

 const AssignServiceButton=() => {
    return (
        <React.Fragment>
        <Link to="/assignService"
        className="btn btn-lg btn-info">
        Assign Service
        </Link>
        </React.Fragment>
    )
};
export default AssignServiceButton;