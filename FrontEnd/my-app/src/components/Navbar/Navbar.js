import React, { Component } from 'react'
import {MenuItems} from "./MenuItems"
import '../../css/Navbar.css'


class Navbar extends Component {

    render() {
        let loggedin = MenuItems.SignedInCustomer;
        let  hasUser = this.props.user.username;
        let userType = this.props.user.userType;
        const items = () => {
            if(hasUser !== null) {
                if(userType === 'customer' || userType === 'worker') {
                    return <ul className = "navbar-items">
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