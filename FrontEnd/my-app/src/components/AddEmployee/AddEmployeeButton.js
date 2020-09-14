import React from 'react'
import {link, Link} from "react-router-dom";

 const AddEmployeeButton=() => {
    return (
        <React.Fragment>
        <Link to="/addEmployee"
        className="btn btn-lg btn-info">
        Add Employee
        </Link>
        </React.Fragment>
    )
};
export default AddEmployeeButton;