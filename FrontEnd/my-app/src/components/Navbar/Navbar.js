import React, { Component } from 'react'
import {MenuItems} from "./MenuItems"
import '../../css/Navbar.css'
import { Link } from "react-router-dom";
import { authenticate, checkUser } from '../../actions/auth';


class Navbar extends Component {

    constructor() {
        super();

        this.state = {
            user: {
                username: 'guest',
                userType: 'guest'
            }
        }
    }

    componentDidMount() {

        if(checkUser()) {
            // console.log("chkUSer: ", checkUser())
            this.setState({user: authenticate()}, () => {
                console.log("NB user: ", this.state.user)
            })
        }

        
    }

    logOut() {

        const createHistory = require("history").createBrowserHistory;

        let history = createHistory();
        history.push("/")
        let pathUrl = window.location.href;
        window.location.href = pathUrl;  

        // clear storage
        localStorage.removeItem("AGMEuser")
        localStorage.removeItem("userType")
        localStorage.removeItem("jwtToken")

    }

    render() {
        
        const logoLink = () => {
            return "/dashboard"
        }

        const user =() => {
            // return MenuItems.NoUser;
            var string;

            if(this.state.user.userType === 'customer') {
                string =  MenuItems.SignedInCustomer
            } else if(this.state.user.userType === 'employee') {
                string =  MenuItems.SignedInWorker
            } else if(this.state.user.userType === 'admin') {
                string = MenuItems.SignedInAdmin
            }  else if(this.state.user.userType === 'guest') {
                string = MenuItems.NoUser
            }              
            
            return string;
        }
        
        return (
            <nav className = "Navbar sticky-top">
                    <a className="navbar-logo" href={logoLink()}></a>
                    <ul className = "navbar-items">
                            {
                                this.state.user !== null && <li><Link to={{pathname: `/profile`}} className = "nav-link">Profile </Link></li>
                            }

                                {user().map((item, index) => {
                                    if(item['title'] === "Log Out") {
                                        return (
                                            <li key = {index}>
                                                <a className = {item.cName} onClick = { this.logOut }>
                                                    {item.title}
                                                </a>
                                            </li>
                                        )
                                    }
                                    else {
                                        return (
                                            <li key = {index}>
                                                <a className = {item.cName} href = {item.url}>
                                                    {item.title}
                                                </a>
                                            </li>
                                        )
                                    }
                                })}
                    </ul>
            </nav>
        );
    }
}

export default Navbar;