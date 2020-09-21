import React, { Component } from 'react'
import axios from "axios";
import '../../css/Searchbar.css'
import Results from './ResultsList';

class Searchbar extends Component {
    
    constructor() {
        super();

        this.state = {
            custID: 'cust5',
            service: '',
            location: '',
            worker: '',
            time: '',
            date: '',
            loading: false,
            searched: false,
            results: null,
            value:''
        };
    }


    validate() {
        return true;
    }

    onSubmit = e => {

        e.preventDefault();
        this.state.searched = true;

        const searchQuery = {
            service: this.state.service,
            location: this.state.location,
            worker: this.state.worker,
            time: this.state.time,
            date:this.state.date
        }
        
        console.log(searchQuery);

        // Validation

        if(this.validate()) {
            // this.props.history.push('/searchResults');
            const empID = this.state.worker
            const service = this.state.service
            const url = 'http://localhost:8080/api/workinghours/list/' + empID + '/' + service
            this.setState({ loading: true });
            axios.get(url, {
            // headers: { 'Authorization': authorization }
            })
            .then(res => {
                console.log(res.data)
                this.setState({
                results: res.data,
                loading: false,
                searched: true
                })
            })
            .catch((error) => {
                console.log("error",error)
            })
        }
        else {
            alert("Invalid search");
        }
    }

    myChangeHandler = event => {
        this.setState({[event.target.name]: event.target.value})
    };

    get results() {
        let results;
        if (this.state.searched){
            results = <h1>There's no available slots</h1>;
        }
        
        if (this.state.results) {
          results = <Results list={this.state.results} />;
        }
    
        return results;
    }

    render() {
        return (
            <div>
                <p id='search-label'>Service Booking Availability</p>
                <form action='' onSubmit={ this.onSubmit }>
                    <input 
                    name= "service"
                    value = { this.state.service }
                    onChange={this.myChangeHandler}
                    type='text' 
                    placeholder='Service' 
                    pattern='[A-Za-z]+' 
                    required='required'
                    title='Alphabetical characters only'/>

                    <input 
                    name= "location"
                    value = { this.state.location }
                    onChange={this.myChangeHandler}
                    type='text' 
                    placeholder='Location' 
                    pattern='[A-Za-z]+' 
                    required='required'
                    title='Alphabetical characters only'/>

                    <input 
                    name= "worker"
                    value = { this.state.worker }
                    onChange={this.myChangeHandler}
                    type='text' 
                    placeholder='Worker' 
                    required='required'
                    //pattern='[A-Za-z]+' 
                    title='Alphabetical characters only'/>

                    <input
                    name= "time" 
                    value = { this.state.time }
                    onChange={this.myChangeHandler}
                    type='time' 
                    placeholder='Time' 
                    required='required'/>

                    <input 
                    name= "date"
                    value = { this.state.date }
                    onChange={this.myChangeHandler}
                    type='date' 
                    placeholder='Time' 
                    required='required'
                    ref={this.dateRef}
                    // onChange={validateDate}
                    />
                    <button type='submit'>Search</button>
                </form>
                {this.results}
            </div>
        )
    }
}

export default Searchbar;