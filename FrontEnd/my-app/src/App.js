import React, { Component } from 'react';
import './App.css';
import './css/Form.css'
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router,Route, Switch } from 'react-router-dom';
import Searchbar from './components/SearchAvailability/Searchbar';
import SearchPage from './components/SearchAvailability/SearchPage';
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
import Homepage from './components/Homepage/Homepage'
import WorkerPage from './components/Worker/WorkerPage'
import WorkerBooking from './components/Bookings/WorkerBooking';
import AddEmployee from './components/Admin/AddEmployee/AddEmployee';
import Admin from './components/Admin/AdminPage';
import AddService from './components/Admin/AddService/AddService';
import AddWorkHours from './components/AddWorkHours/AddWorkHours';


class App extends Component {
  constructor() {
    super();
    this.state = {
        user: {
          username: "cus6",
          userType: "customer"
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
            <Route exact path="/upcomingBookings" component={UpcomingBookings} />
            <Route exact path="/search" component={Searchbar} />
            <Route exact path="/searchResults" component={SearchPage} />
            <Route exact path="/worker" component={WorkerPage} />
            <Route exact path="/:empId/workinghours" component={WorkerBooking} />
            <Route exact path="/homepage" component={Homepage} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/admin" component={Admin} />
            <Route exact path="/addEmployee" component={() => <AddEmployee user = {this.state.user} />} /> 
            <Route exact path="/addWorkhours" component={AddWorkHours} />  
            <Route exact path="/addService" component={AddService} />                                  
          </div>
        </div>
      </Router>
    </Provider>
  );
  }
}
export default App;