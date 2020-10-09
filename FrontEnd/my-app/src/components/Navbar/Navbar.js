import React, { Component } from 'react'
import {MenuItems} from "./MenuItems"
import '../../css/Navbar.css'
import { Link } from "react-router-dom";


class Navbar extends Component {

    constructor() {
        super();
        this.state = {
            user: {
              username: 'guest',
              userType: 'guest'
            },

            hasUser: false
        }

        if(this.props) {
            this.state.hasUser = this.props.user !== null;
        }

        if(this.state.hasUser){
            
            this.state.user.userType = this.props.user.userType
            this.state.user.username = this.props.user.username
            
        } 
    }

    render() {
        
        const logoLink = () => {
            if(this.state.hasUser) {
                return "/dashboard"
            } else {
                return "/"
            }
        }

        const user =() => {
            // return MenuItems.NoUser;
            var string;
            if(this.state.hasUser) {
                if(this.user.userType === 'customer') {
                    string =  MenuItems.SignedInCustomer
                } else if(this.user.userType === 'employee') {
                    string =  MenuItems.SignedInWorker
                } else if(this.user.userType === 'admin') {
                    string = MenuItems.SignedInAdmin
                }  else if(this.user.userType === 'guest') {
                    string = MenuItems.NoUser
                }              
            } else {
                string = MenuItems.NoUser
            }
            return string;
        }
        
        return (
            <nav className = "Navbar sticky-top">
                    <a className="navbar-logo" href={logoLink()}></a>
                    <ul className = "navbar-items">
                            {
                                this.state.hasUser &&  <li><Link to={{pathname: `/profile`}} className = "nav-link">Profile </Link></li>
                            }

                                {user().map((item, index) => {
                                    return (
                                        <li key = {index}>
                                            <a className = {item.cName} href = {item.url}>
                                                {item.title}
                                            </a>
                                        </li>
                                    )
                                })}
                    </ul>
            </nav>
        );
    }
}

export default Navbar;