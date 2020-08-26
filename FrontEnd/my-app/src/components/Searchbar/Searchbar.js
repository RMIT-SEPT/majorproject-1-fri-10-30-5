import React, { Component } from 'react'
import './Searchbar.css'

class Searchbar extends Component {

    // constructor() {

    //     this.dateRef = React.createRef();

    //     function validateDate() {
    //         dateRef.
    //     }
    // }

    render() {
        return (
            <div>
                <p id='search-label'>Service Booking Availability</p>
                <form method='get' action=''>
                    <input 
                    type='text' 
                    placeholder='Service' 
                    pattern='[A-Za-z]+' 
                    required='required'
                    title='Alphabetical characters only'>
                    </input>
                    <input 
                    type='text' 
                    placeholder='Location' 
                    pattern='[A-Za-z]+' 
                    required='required'
                    title='Alphabetical characters only'>
                    </input>
                    <input 
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
            </div>
        )
    }
}

export default Searchbar;