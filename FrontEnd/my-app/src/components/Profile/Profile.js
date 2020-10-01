import React from 'react';
import  "../../css/Profile.css";
import PropTypes from 'prop-types';
import {link, Link} from "react-router-dom";

const Profile = (props) => {
  return (
    <div>
      <h1>Profile for {props.username}</h1>
      <hr/>
      <ul className = "profile-items">
        <li key="fname">First Name: {props.fname}</li>
        <li key="lname">Last Name: {props.lname}</li>
        <li key="phone">Phone: {props.phone}</li>
        <li key="address">Address: {props.address}</li>
      </ul>
      <hr/>
      <br />
      <Link to={{
        pathname: `/profile/${props.username}/edit`
      }} 
        className="btn btn-lg btn-info" style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>
      Edit Details
     </Link>
      <br />
    </div>
  )
};

Profile.propTypes = {
  username: PropTypes.string.isRequired,
  address: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  phone: PropTypes.string.isRequired
};

export default Profile;
