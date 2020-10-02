import React from 'react'
import {Link} from "react-router-dom";

 const SearchPageButton=() => {
    return (
        <React.Fragment>
        <Link to="/search"
        className="btn btn-lg btn-info">
        Make a Booking
        </Link>
        </React.Fragment>
    )
};
export default SearchPageButton;