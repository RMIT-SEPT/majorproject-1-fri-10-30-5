import React, { Component } from 'react'
import PastBookingsButton from './Bookings/PastBookingsButton';
import UpcomingBookingsButton from './Bookings/UpcomingBookingsButton';
import ProfileButton from './Profile/ProfileButton';

class Dashboard extends Component {
    render() {
        return (
            <div className="Dashboard">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Dashboard</h1>
                        <br />
                        <ProfileButton />
                         <br />
                        <br />
                       <UpcomingBookingsButton />
                        <br />
                        <br />
                        <PastBookingsButton />
                         <br />

                    </div>
                </div>
            </div>
        </div>
    
        )
    }
}
export default Dashboard;
