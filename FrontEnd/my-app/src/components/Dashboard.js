import React, { Component } from "react";
import "../css/Dashboard.css";
import PastBookingsButton from "./Bookings/PastBookingsButton";
import UpcomingBookingsButton from "./Bookings/UpcomingBookingsButton";
import SearchPageButton from "./SearchAvailability/SearchPageButton";
import { link, Link } from "react-router-dom";

class Dashboard extends Component {
  render() {
    return (
      <div className="Dashboard">
        <h1 className="display-4 text-center">Dashboard</h1>
        <br />
        <h1 className="welcome">Welcome, {this.props.user.username}!</h1>
        <br />
        <Link
          to={{
            pathname: `/profile/${this.props.user.username}`,
          }}
          className="btn btn-lg btn-info"
          style={{ backgroundColor: "#341930", border: "1px solid #341930" }}
        >
          Profile
        </Link>
        <br />
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
