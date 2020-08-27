import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import './Searchbar.css'

class Searchbar extends Component {

    constructor() {
        super();

        this.state = {
            service: "",
            location: "",
            worker: ""
        };

        this.onChangeLoc = this.onChangeLoc.bind(this);
        this.onChangeWorker = this.onChangeWorker.bind(this)
        this.onChangeService = this.onChangeService.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }

    onChangeLoc(e) {
        this.setState({location: e.target.value});
    }

    onChangeWorker(e) {
        this.setState({worker: e.target.value});
    }

    onChangeService(e) {
        this.setState({service: e.target.value});
    }

    validate() {
        return true;
    }

    onSubmit(e) {

        e.preventDefault();

        const searchQuery = {
            service: this.state.service,
            location: this.state.location,
            worker: this.state.worker
        }
        
        console.log(searchQuery);

        // Validation

        if(this.validate()) {
            this.props.history.push('/searchResults');
        }
        else {
            alert("Invalid search");
        }
        
    }

    render() {
        return (
            <React.Fragment>
                <p id='search-label'>Service Booking Availability</p>
                <form method='get' action='' onSubmit={this.onSubmit}>
                    <input 
                    value = { this.state.service }
                    onChange= { this.onChangeService }
                    type='text' 
                    placeholder='Service' 
                    pattern='[A-Za-z]+' 
                    required='required'
                    title='Alphabetical characters only'>
                    </input>

                    <input 
                    value = { this.state.location }
                    onChange= { this.onChangeLoc }
                    type='text' 
                    placeholder='Location' 
                    pattern='[A-Za-z]+' 
                    required='required'
                    title='Alphabetical characters only'>
                    </input>

                    <input 
                    value = { this.state.worker }
                    onChange= { this.onChangeWorker }
                    type='text' 
                    placeholder='Worker' 
                    required='required'
                    pattern='[A-Za-z]+' 
                    title='Alphabetical characters only'>
                    </input>

                    <input 
                    type='time' 
                    placeholder='Time' 
                    required='required'>
                    </input>

                    <input 
                    type='date' 
                    placeholder='Time' 
                    required='required'
                    ref={this.dateRef}
                    // onChange={validateDate}
                    >

                    </input>
                    <button type='submit'>Search</button>
                </form>
            </React.Fragment>
        )
    }
}

export default Searchbar;