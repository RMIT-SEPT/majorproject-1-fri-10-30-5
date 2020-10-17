import React, { Component } from "react";
import "../css/Dashboard.css";
import PastBookingsButton from "./BookingData/PastBookingsButton";
import UpcomingBookingsButton from "./BookingData/UpcomingBookingsButton";
import SearchPageButton from "./Search/SearchPageButton";
import axios from 'axios'
import { withRouter } from 'react-router-dom'
import { authenticate } from './../actions/auth'

class Dashboard extends Component {

  constructor() {
    super()

    this.state = {
      user: authenticate()
    }
  }

  render() {

    return (
      <div className="Dashboard">
        <h1 className="display-4 text-center">Dashboard</h1>
        <br />
        <h1 className="welcome">Welcome, {this.state.user.username}!</h1>
        <br />
        <UpcomingBookingsButton />
        <br />
        <br />
        <PastBookingsButton />
        <br />
        <br />
        <SearchPageButton />
        <br />
      </div>
    );
  }
}
export default withRouter(Dashboard);
