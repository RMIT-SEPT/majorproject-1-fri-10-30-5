import React from 'react'
import {link, Link} from "react-router-dom";

 const ProfileEditButton=() => {
    return (
        <React.Fragment>
        <Link to="profile/edit"
        className="btn btn-lg btn-info">
        Edit details
        </Link>
        </React.Fragment>
    )
};
export default ProfileEditButton;