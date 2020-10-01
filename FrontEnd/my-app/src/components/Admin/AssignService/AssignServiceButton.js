import React from 'react'
import {link, Link} from "react-router-dom";

 const AssignServiceButton=() => {
    return (
        <React.Fragment>
        <Link to="/assignService"
        className="btn btn-lg btn-info" style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>
        Assign Service
        </Link>
        </React.Fragment>
    )
};
export default AssignServiceButton;