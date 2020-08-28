import React from 'react'
import {link, Link} from "react-router-dom";

 const ProfileButton=() => {
    return (
        <React.Fragment>
        <Link to="/profile"
        className="btn btn-lg btn-info">
        View Profile
        </Link>
        </React.Fragment>
    )
};
export default ProfileButton;
