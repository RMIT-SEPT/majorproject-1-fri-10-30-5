import React, { Component } from 'react'
import axios from "axios";
import '../../css/Searchbar.css'
import ServiceResultsList from './ServiceSearch/ServiceResultsList'
import WorkerResultsItem from './WorkerResultsItem';

class Searchbar extends Component {
    
    constructor() {
        super();

        this.state = {
            searchType: 'service',

            // results listed when searching for a service
            testServiceRes: ["emp5", "emp6"],

            // results listed when searching for a worker
            testWorkerRes: ["emp10", "emp5", "emp6"],
            
            results: null,
        };

        this.onChangeSearchType = this.onChangeSearchType.bind(this)
    }


    validate() {
        return true;
    }

    onSubmit = e => {

        e.preventDefault();

        // service query
        if(this.state.searchType === 'service') {
          this.runServiceQuery()
       }

        // worker query
        else if(this.state.searchType === 'worker') {
          this.runWorkerQuery()
        }
       
    }

    runWorkerQuery = e => {

      // query the database to check if a worker exists
      let found = false;

      if(this.validate()) {
        console.log(this.state.search)
        const url = 'http://localhost:8080/api/employee/' + this.state.search
        axios.get(url, {
        // headers: { 'Authorization': authorization }
        })
        .then(res => {
          console.log("found! ", res.data)
          found = true;
          this.setState({
          results: res.data["userName"]
          })
        })
        .catch((error) => {
          console.log("error",error)
        })
      }
      else {
        alert("Invalid search");
      }

      // remove stored results 
      if(!found) {
        this.setState({results: null})
      }
    }

    runServiceQuery = e => {

      // query the database to retrieve all workers that do the service
      /*
          get list of workers
          for each worker get their assigned services
          add that to list X
          results = list X
      */
      // if(this.validate()) {
      //   console.log(this.state.search)
      //   const url = 'http://localhost:8080/api/employee/' + this.state.search
      //   axios.get(url, {
      //   // headers: { 'Authorization': authorization }
      //   })
      //   .then(res => {
      //     console.log("found! ", res.data)
      //     this.setState({
      //     results: res.data["userName"]
      //     })
      //   })
      //   .catch((error) => {
      //     console.log("error",error)
      //   })
      // }
      // else {
      //   alert("Invalid search");
      // }

      // test result
      this.setState({results: this.state.testServiceRes});
    }

    onChangeRadioButtonState = event => {

      this.setState({[event.target.name]: event.target.value})
    };

    get results() {

      let results
      
      console.log("results: ", this.state.results)

      if(this.state.searchType === "service") {
        if (this.state.results) {
          results = <ServiceResultsList list={this.state.results}/>
        }
      }

      else  if(this.state.searchType === "worker") {

        if(this.state.results !== null) {
          results = <WorkerResultsItem name={this.state.results}/>
        } else {
          return (<p>Sorry! We couldn't find a worker by that name</p>)
        }
      }

      return results
    }

    onChangeSearchType = e => {

      this.setState({searchType: e.target.value, results: null})
    }

    render() {
      return (
      <div id='search'>
        <h1 className="display-4 text-center title">Make a Booking</h1>
        <p>Bookings can be made through a simple search of service, or through the timetable of a preferred, available worker.</p>
        <form action='' onSubmit={ this.onSubmit }>
          <div id='radio-group'>
            <input 
              id='service' 
              type='radio' 
              name='search' 
              value='service' 
              onClick={this.onChangeSearchType}
              required='required'
            />
            <label for='service'>Service</label><br/>
            <input 
              id='worker' 
              type='radio' 
              name='search' 
              value='worker'
              onClick={this.onChangeSearchType}
            />
            <label for='worker'>Worker</label><br/>
            <input className = "search-bar"
              id='search-bar'
              name= "search"
              value = { this.state.search }
              onChange={this.onChangeRadioButtonState}
              type='text' 
              placeholder='Search' 
              required='required'
              title='Alphabetical characters only'
            />
          </div>
            <button className = "buttons" type='submit'>Search</button>
        </form>
        {this.results}
      </div>
    )
  }
}

export default Searchbar;