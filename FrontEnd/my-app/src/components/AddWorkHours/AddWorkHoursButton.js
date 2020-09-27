import React from 'react'
import {link, Link} from "react-router-dom";

 const AddWorkHoursButton=() => {
    return (
        <React.Fragment>
        <Link to="/addWorkhours"
        className="btn btn-lg btn-info">
        Add Working Hours
        </Link>
        </React.Fragment>
    )
};
export default AddWorkHoursButton;