import React from 'react';
import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter as Router, Route} from "react-router-dom";
import PastBookings from './components/Bookings/PastBookings';
import Profile from "./components/Profile/Profile";
import UpcomingBookings from "./components/Bookings/UpcomingBookings";
import {Provider} from "react-redux";
import store from './store';


function App() {
  return (
    <Provider store={store}>
    <Router>
    <div>
     <Header/>
     <Route exact path="/dashboard" component={Dashboard} />
     <Route exact path="/pastBookings" component={PastBookings} />
     <Route exact path="/profile" component={Profile} />
     <Route exact path="/upcomingBookings" component = {UpcomingBookings} />
    </div>
    </Router>
    </Provider>
  );
}

export default App;
 