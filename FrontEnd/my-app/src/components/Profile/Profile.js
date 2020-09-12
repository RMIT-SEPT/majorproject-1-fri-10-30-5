import React from 'react';
import PropTypes from 'prop-types';
import ProfileEditButton from './ProfileEditButton';

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
      <ProfileEditButton />
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
