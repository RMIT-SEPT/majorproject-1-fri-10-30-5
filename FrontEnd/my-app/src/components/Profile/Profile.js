import React from 'react';
import PropTypes from 'prop-types';
import {link, Link} from "react-router-dom";

const Profile = (props) => {
  return (
    <div>
      <h1>Profile for {props.username}</h1>
      <hr/>
      <ul >
        <li key="name">Name: {props.name}</li>
        <li key="phone">Phone: {props.phone}</li>
        <li key="address">Address: {props.address}</li>
      </ul>
      <hr/>
      <br />
      <Link to={{
        pathname: `/profile/${props.username}/edit`
      }} 
        className="btn btn-lg btn-info" >
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
