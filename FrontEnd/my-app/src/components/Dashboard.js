import React, { Component } from "react";
import "../css/Dashboard.css";
import PastBookingsButton from "./BookingData/PastBookingsButton";
import UpcomingBookingsButton from "./BookingData/UpcomingBookingsButton";
import SearchPageButton from "./Search/SearchPageButton";

class Dashboard extends Component {

  test() {
    console.log("props: ", this.props)
  }

  render() {

    this.test()
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
