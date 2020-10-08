import React, { Component } from 'react';
import './App.css';
import './css/Form.css'
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router,Route, Switch } from 'react-router-dom';
import Searchbar from './components/SearchAvailability/Searchbar';
import Dashboard from './components/Dashboard';
import Header from './components/Navbar/Navbar';
import PastBookings from './components/Bookings/PastBookings';
import Profile from "./components/Profile/ProfilePage";
import ProfileEdit from "./components/Profile/ProfileEditPage";
import UpcomingBookings from "./components/Bookings/UpcomingBookings";
import {Provider} from "react-redux";
import store from './store';
import Login from './components/Login/LoginForm';
import Register from './components/Register/RegisterForm';
import ContactUs from './components/Homepage/ContactUs'
import AboutUs from './components/Homepage/AboutUs'
import WorkerPage from './components/SearchAvailability/WorkerPage'
import WorkerBooking from './components/SearchAvailability/WorkerBooking';
import AddEmployee from './components/Admin/AddEmployee/AddEmployee';
import Admin from './components/Admin/AdminPage';
import AddService from './components/Admin/AddService/AddService';
import AddWorkHours from './components/Admin/AddWorkHours/AddWorkHours';
import WorkerNavPage from './components/Worker/WorkerNavPage';
import AssignService from './components/Admin/AssignService/AssignService';
import AdminUpcoming from './components/Admin/AdminBookings/AdminUpcoming';
import AdminPast from './components/Admin/AdminBookings/AdminPast';
import BookingSuccess from './components/SearchAvailability/BookingLanding/BookingSuccess';
import BookingFailure from './components/SearchAvailability/BookingLanding/BookingFailure';

class App extends Component {
  constructor() {
    super();
    this.state = {
        user: {
          username: "cus6",
          userType: "admin"
        }
    }
  }

render() {

  return (
    <Provider store={store}>
      <Router>
        <div className="app">
          <Header user = {this.state.user}/>
          <div className = "page-container">
            <Route exact path="/dashboard" component={() => <Dashboard user= {this.state.user}/>}  />
            <Route exact path="/pastBookings" component={() => <PastBookings user = {this.state.user}/>} />
            <Route exact path="/profile/:id" component={() => <Profile user= {this.state.user}/>} />
            <Route exact path="/profile/:id/edit" component={ProfileEdit} />
            <Route exact path="/upcomingBookings" component={() => <UpcomingBookings user= {this.state.user}/>} />
            <Route exact path="/search" component={Searchbar} />
            <Route exact path="/worker" component={WorkerPage} />
<<<<<<< HEAD
            <Route exact path="/:empId/booking" component={WorkerBooking} />
            <Route exact path="/homepage" component={Homepage} />
=======
            <Route exact path="/:empId/workinghours" component={WorkerBooking} />
            <Route exact path="/contact-us" component={ContactUs} />
            <Route exact path="/about-us" component={AboutUs} />
>>>>>>> registerUser
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/admin" component={Admin} />
            <Route exact path="/addEmployee" component={() => <AddEmployee user = {this.state.user} />} /> 
            <Route exact path="/addWorkhours" component={AddWorkHours} />  
            <Route exact path="/addService" component={AddService} />     
            <Route exact path="/workerPage" component = {WorkerNavPage} />          
            <Route exact path="/assignService" component = {AssignService} />  
            <Route exact path="/admin/upcoming" component = {AdminUpcoming} />     
<<<<<<< HEAD
            <Route exact path="/admin/past" component = {AdminPast} />   
            <Route exact path="/:empId/roster" component = {WorkerHours} />               
=======
            <Route exact path="/admin/past" component = {AdminPast} /> 
            <Route exact path="/bookingSuccess" component = {BookingSuccess} /> 
            <Route exact path="/bookingFailed" component = {BookingFailure} /> 

>>>>>>> registerUser
          </div>
        </div>
      </Router>
    </Provider>
  );
  }
}
export default App;