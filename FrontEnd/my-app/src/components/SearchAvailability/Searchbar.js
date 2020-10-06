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
        const url = 'http://localhost:8080/api/person/employee/' + this.state.search
        axios.get(url, {})
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
      let found = false;
      if(this.validate()) {

        console.log(this.state.search)
    
        // get service id
        let url = 'http://localhost:8080/api/service/list'
        axios.get(url, {})
        .then(res => {

          for(let i = 0; i < Object.keys(res.data).length; i++) {
            if(res.data[i]["name"] === this.state.search) {

              this.setState({serviceId: res.data[i]["serviceId"]}, () => {

                // get list of employees from service id
                url = 'http://localhost:8080/api/assignService/employee-list/' + this.state.serviceId
                axios.get(url, {})
                .then(res => {
                  found = true
                  console.log("found! ", res.data)

                    let usernames = []
                    for(let i = 0; i < Object.keys(res.data).length; i++) {
                      usernames.push(res.data[i]["userName"])
                    }

                  this.setState({
                    results: usernames
                  })
                })
                .catch((error) => {
                  console.log("error",error)
                })
              })

              console.log(res.data[i])
              console.log("found service!: ", res.data[i]["name"] + " with id " + this.state.serviceId)
            }
          }
        })
        .catch((error) => {
          console.log("error",error)
        })
      }

      // remove stored results 
      if(!found) {
        this.setState({results: null})
      }

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