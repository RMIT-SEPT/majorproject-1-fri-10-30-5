import React, { Component } from 'react'
import {MenuItems} from "./MenuItems"
import '../../css/Navbar.css'
import { Link } from "react-router-dom";


class Navbar extends Component {

    render() {
        const  hasUser = this.props.user !== null;
        let userType;
        if(hasUser){
            userType = this.props.user.userType;
        }else{
            userType = "none"
        }
        
        const logoLink = () => {
            if(hasUser) {
                return "/dashboard"
            } else {
                return "/"
            }
        }

        const user =() => {
            return MenuItems.NoUser;
            var string;
            if(hasUser) {
                if(userType === 'customer') {
                    string =  MenuItems.SignedInCustomer
                } else if(userType === 'employee') {
                    string =  MenuItems.SignedInWorker
                } else if(userType === 'admin') {
                    string = MenuItems.SignedInAdmin
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
                                hasUser &&  <li><Link to={{pathname: `/profile`}} className = "nav-link">Profile </Link></li>
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