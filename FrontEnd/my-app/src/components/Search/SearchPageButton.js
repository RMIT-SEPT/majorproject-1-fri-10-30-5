import React from 'react'
import {Link} from "react-router-dom";

 const SearchPageButton=() => {
    return (
        <React.Fragment>
        <Link to="/search"
        className="btn btn-lg btn-info"
        style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>
        Make a Booking
        </Link>
        </React.Fragment>
    )
};
export default SearchPageButton;