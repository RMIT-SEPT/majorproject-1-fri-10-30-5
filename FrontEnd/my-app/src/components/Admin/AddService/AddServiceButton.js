import React from 'react'
import {link, Link} from "react-router-dom";

 const AddServiceButton=() => {
    return (
        <React.Fragment>
        <Link to="/addService"
        className="btn btn-lg btn-info" style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>
        Add Service
        </Link>
        </React.Fragment>
    )
};
export default AddServiceButton;