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

        this.onChangeSearchType = this.onChangeSearchType.bind(this)
    }


    validate() {
        return true;
    }

    onSubmit = e => {

        e.preventDefault();
        this.state.searched = true;
        
        console.log("searchType: ", this.state.searchType, "\nsearch: ", this.state.search)

        // service query
        // should return the list of employees offering the service
        if(this.state.searchType === 'service') {
            
            this.runServiceQuery()
       }

        // worker query
        // should return the worker's timetable
        else if(this.state.searchType === 'worker') {

            this.runWorkerQuery()

        }
       
    }

    runWorkerQuery = e => {

      // query the database to check if a worker exists
      // if(this.validate()) {
      //   console.log(this.state.search)
      //   const url = 'http://localhost:8080/api/user/' + this.state.search
      //   this.setState({ loading: true });
      //   axios.get(url, {
      //   // headers: { 'Authorization': authorization }
      //   })
      //   .then(res => {
      //     console.log(res.data)
      //     this.setState({
      //     results: res.data,
      //     loading: false,
      //     searched: true
      //     })
      //   })
      //   .catch((error) => {
      //     console.log("error",error)
      //   })
      // }
      // else {
      //   alert("Invalid search");
      // }

      let found = false;

      this.state.testWorkerRes.map((emp) => {
        console.log(emp)
        if (emp.localeCompare(this.state.search) == 0 && !found) {
          this.setState({results: emp})
          found = true;
          console.log("found")
        }
      })

      // test result
      if(!found)
        this.setState({results: null})

    }

    runServiceQuery = e => {

      // query the database to retrieve all workers that do the service
      // if(this.validate()) {
      //   const empID = this.state.worker
      //   const service = this.state.service
      //   const url = 'http://localhost:8080/api/workinghours/list/' + empID + '/' + service
      //   this.setState({ loading: true });
      //   axios.get(url, {
      //   // headers: { 'Authorization': authorization }
      //   })
      //   .then(res => {
      //     console.log(res.data)
      //     this.setState({
      //     results: res.data,
      //     loading: false,
      //     searched: true
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
        <h1 className="display-4 text-center">Make a Booking</h1>
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
            <input 
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
            <button type='submit'>Search</button>
        </form>
        {this.results}
      </div>
    )
  }
}

export default Searchbar;