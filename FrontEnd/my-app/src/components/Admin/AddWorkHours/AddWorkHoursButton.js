import React from 'react'
import {link, Link} from "react-router-dom";

 const AddWorkHoursButton=() => {
    return (
        <React.Fragment>
        <Link to="/addWorkhours"
        className="btn btn-lg btn-info"  style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>
        Add Working Hours     
        </Link>
        </React.Fragment>
    )
};
export default AddWorkHoursButton;