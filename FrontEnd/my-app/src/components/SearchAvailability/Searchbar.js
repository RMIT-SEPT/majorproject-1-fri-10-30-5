import React, { Component } from 'react'
import axios from "axios";
import '../../css/Searchbar.css'
import Results from './ResultsList';

class Searchbar extends Component {
    
    constructor() {
        super();

        this.state = {
            searchType: 'service',
            

            custID: 'cus5',
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

        this.onChangeRadio = this.onChangeRadio.bind(this)
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

    onChangeRadio = e => {

        this.setState({searchType: e.target.value}, () => {
            console.log(this.state.searchType)
        })
    }

    render() {
        return (
            <div>
                <p id='search-label'>Service Booking Availability</p>
                <form action='' onSubmit={ this.onSubmit }>

                <input 
                    id='service' 
                    type='radio' 
                    name='search' 
                    value='service' 
                    onClick={this.onChangeRadio}
                />
                <label for='service'>Service</label><br/>
                <input 
                    id='worker' 
                    type='radio' 
                    name='search' 
                    value='worker'
                    onClick={this.onChangeRadio}
                />
                <label for='worker'>Worker</label><br/>

                <input 
                    name= "search"
                    value = { this.state.search }
                    onChange={this.myChangeHandler}
                    type='text' 
                    placeholder='Search' 
                    pattern='[A-Za-z]+' 
                    required='required'
                    title='Alphabetical characters only'/>

                    <button type='submit'>Search</button>
                </form>
                {this.results}
            </div>
        )
    }
}

export default Searchbar;