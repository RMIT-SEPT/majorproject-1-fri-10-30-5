import React, { Component } from 'react';
import './App.css';
import './css/Form.css'
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router,Route, Switch } from 'react-router-dom';
import Searchbar from './components/Search/Searchbar';
import Dashboard from './components/Dashboard';
import Header from './components/Navbar/Navbar';
import PastBookings from './components/BookingData/PastBookings';
import Profile from "./components/Profile/ProfilePage";
import ProfileEdit from "./components/Profile/ProfileEditPage";
import UpcomingBookings from "./components/BookingData/UpcomingBookings";
import {Provider} from "react-redux";
import store from './store';
import Login from './components/Login/LoginForm';
import Register from './components/Register/RegisterForm';
import ContactUs from './components/Homepage/ContactUs'
import AboutUs from './components/Homepage/AboutUs'
import WorkerPage from './components/Worker/WorkerPage'
import Booking from './components/Worker/Calendar/Booking';
import Roster from './components/Worker/Calendar/Roster'
import AddEmployee from './components/Admin/AddEmployee/AddEmployee';
import Admin from './components/Admin/AdminPage';
import AddService from './components/Admin/AddService/AddService';
import AddWorkHours from './components/Admin/AddWorkHours/AddWorkHours';
import WorkerNavPage from './components/Worker/WorkerNavPage';
import AssignService from './components/Admin/AssignService/AssignService';
import AdminUpcoming from './components/Admin/AdminBookings/AdminUpcoming';
import AdminPast from './components/Admin/AdminBookings/AdminPast';
import BookingSuccess from './components/Search/BookingLanding/BookingSuccess';
import BookingFailure from './components/Search/BookingLanding/BookingFailure';
import WorkerSuccess from './components/Admin/AddEmployee/WorkerSuccess';

class App extends Component {

render() {

  return (
    <Provider store={store}>
      <Router>
        <div className="app">
            <Header />
            <div className="page-container">
              <Route exact path="/dashboard" component={() => <Dashboard />}  />
              <Route exact path="/pastBookings" component={() => <PastBookings />} />
              <Route exact path="/profile" component={() => <Profile />} />
              <Route exact path="/profileEdit" component={ProfileEdit} />
              <Route exact path="/upcomingBookings" component={() => <UpcomingBookings/>} />
              <Route exact path="/search" component={Searchbar} />
              <Route exact path="/worker" component={WorkerPage} />
              <Route exact path="/:empId/booking" component={Booking} />
              <Route exact path="/contact-us" component={ContactUs} />
              <Route exact path="/about-us" component={AboutUs} />
              <Route exact path="/" component={Login} />
              <Route exact path="/login" component={Login} />
              <Route exact path="/register" component={Register} />
              <Route exact path="/admin" component={Admin} />
              <Route exact path="/addEmployee" component={() => <AddEmployee  />} /> 
              <Route exact path="/addWorkhours" component={AddWorkHours} />  
              <Route exact path="/addService" component={AddService} />     
              <Route exact path="/workerPage" component = {WorkerNavPage} />          
              <Route exact path="/assignService" component = {AssignService} />  
              <Route exact path="/admin/upcoming" component = {AdminUpcoming} />     
              <Route exact path="/:empId/roster" component = {Roster} />               
              <Route exact path="/admin/past" component = {AdminPast} /> 
              <Route exact path="/bookingSuccess" component = {BookingSuccess} /> 
              <Route exact path="/bookingFailed" component = {BookingFailure} />
              <Route exact path="/workerSuccess" component = {WorkerSuccess} />
            </div>  
        </div>
      </Router>
    </Provider>
  );
  }
}
export default App;