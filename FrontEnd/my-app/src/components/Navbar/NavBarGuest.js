import React, { Component } from 'react'
import {MenuItems} from "./MenuItems"
import '../../css/Navbar.css'
import { Link } from "react-router-dom";


class Navbar extends Component {

    
    
    render() {


        const user = () => { return MenuItems.NoUser }
    
        return (
            <nav className = "Navbar sticky-top">
                    <a className="navbar-logo" href={"/"}></a>
                    <ul className = "navbar-items">

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