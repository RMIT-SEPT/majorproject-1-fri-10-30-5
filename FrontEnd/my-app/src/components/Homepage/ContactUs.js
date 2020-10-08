import React, { Component } from 'react'
import '../../css/Form.css'

class Homepage extends Component {
    render() {
        return (
            <div className = "forms-wrapper">
            <div className = "forms-inner">
            <form id="contact-form">
            <h3>Contact Us</h3>
            <div className="form-group">
                <label htmlFor="name">Name</label>
                <input type="text" className="form-control" />
            </div>
            <div className="form-group">
                <label htmlFor="exampleInputEmail1">Email address</label>
                <input type="email" className="form-control" aria-describedby="emailHelp" />
            </div>
            <div className="form-group">
                <label htmlFor="message">Message</label>
                <textarea className="form-control" rows="5"></textarea>
            </div>
            <button type="submit" className="btn btn-primary" style = {{backgroundColor: "#341930",border: "1px solid #341930"}}>Submit</button>
            </form>
            </div>
            </div>
        )
    }
}

export default Homepage;