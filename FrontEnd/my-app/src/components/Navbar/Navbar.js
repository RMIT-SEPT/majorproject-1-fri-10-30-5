import React, { Component } from 'react'
import {MenuItems} from "./MenuItems"
import '../../css/Navbar.css'
import { Link } from "react-router-dom";


class Navbar extends Component {

    render() {
        let  hasUser = this.props.user.username;
        let userType = this.props.user.userType;
        const items = () => {
            if(hasUser !== null) {
                if(userType === 'customer') {
                    
                    return <ul className = "navbar-items">
                                <li><Link to={{pathname: `/profile/${this.props.user.username}`}} className = "nav-link">Profile </Link></li>
                                {MenuItems.SignedInCustomer.map((item, index) => {
                                    return (
                                        <li key = {index}>

                                            <a className = {item.cName} href = {item.url}>
                                                {item.title}
                                            </a>
                                        </li>
                                    )
                                })}
                            </ul>


                } else if(userType === 'admin') {
                    return <ul className = "navbar-items">
                                <li><Link to={{pathname: `/profile/${this.props.user.username}`}} className = "nav-link">Profile </Link></li>
                                {MenuItems.SignedInAdmin.map((item, index) => {
                                    return (
                                        <li key = {index}>
                                            <a className = {item.cName} href = {item.url}>
                                                {item.title}
                                            </a>
                                        </li>
                                    )
                                })}
                            </ul>
                }  else if (userType === 'worker') {
                    return <ul className = "navbar-items">
                                <li><Link to={{pathname: `/profile/${this.props.user.username}`}} className = "nav-link">Profile </Link></li>
                                {MenuItems.SignedInWorker.map((item, index) => {
                                return (
                                    <li key = {index}>
                                        <a className = {item.cName} href = {item.url}>
                                            {item.title}
                                        </a>
                                    </li>
                                )
                                })}
                            </ul>                
                }
            } else {
                return  <ul className = "navbar-items">
                            {MenuItems.NoUser.map((item, index) => {
                            return (
                                <li key = {index}>
                                    <a className = {item.cName} href = {item.url}>
                                        {item.title}
                                    </a>
                                </li>
                            )
                            })}
                        </ul>                
            }
        }
        return (
            <nav className = "Navbar">
                    <a className="navbar-logo" href="/dashboard"></a>
                    {items()}
            </nav>
        );
    }
}

export default Navbar;