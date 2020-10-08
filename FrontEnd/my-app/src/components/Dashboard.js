import React, { Component } from "react";
import "../css/Dashboard.css";
import PastBookingsButton from "./Bookings/PastBookingsButton";
import UpcomingBookingsButton from "./Bookings/UpcomingBookingsButton";
import SearchPageButton from "./Search/SearchPageButton";
import { Link } from "react-router-dom";

class Dashboard extends Component {
  render() {
    return (
      <div className="Dashboard">
        <h1 className="display-4 text-center">Dashboard</h1>
        <br />
        <h1 className="welcome">Welcome, {this.props.user.username}!</h1>
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
export default Dashboard;
