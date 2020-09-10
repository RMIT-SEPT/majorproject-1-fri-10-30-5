import React from 'react';
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router,Route } from 'react-router-dom';
import Searchbar from './components/SearchAvailability/Searchbar';
import SearchPage from './components/SearchAvailability/SearchPage';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import PastBookings from './components/Bookings/PastBookings';
import Profile from "./components/Profile/ProfilePage";
import ProfileEdit from "./components/Profile/ProfileEditPage";
import UpcomingBookings from "./components/Bookings/UpcomingBookings";
import {Provider} from "react-redux";
import store from './store';
import Login from './components/Login/LoginForm';
import Register from './components/Register/RegisterForm';
import Homepage from './components/Homepage/Homepage'
import WorkerPage from './components/Worker/WorkerPage'
import WorkerBooking from './components/Bookings/WorkerBooking'

function App() {
  return (
    <Provider store={store}>
    <Router>
      <div className="App">
        <Header/>
        <Route exact path="/dashboard" component={Dashboard} />
        <Route exact path="/pastBookings" component={PastBookings} />
        <Route exact path="/profile/:id" component={Profile} />
        <Route exact path="/profile/:id/edit" component={ProfileEdit} />
        <Route exact path="/upcomingBookings" component = {UpcomingBookings} />
        <Route exact path="/search" component={ Searchbar } />
        <Route exact path="/searchResults" component={ SearchPage } />
        <Route exact path="/worker" component={WorkerPage} />
        <Route exact path="/workerBooking" component={WorkerBooking} />

        <Route exact path="/homepage" component={Homepage} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/register" component={Register} />
      </div>
    </Router>
    </Provider>
  );
}

export default App;