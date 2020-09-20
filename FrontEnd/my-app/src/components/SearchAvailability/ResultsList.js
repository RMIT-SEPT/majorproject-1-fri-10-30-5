import React, { Component } from 'react'
import SearchResultsItem from './ResultsItem'
import {Link, Redirect} from 'react-router-dom'
import axios from 'axios'
const createHistory = require("history").createBrowserHistory;

class SearchResultsList extends Component {

    // findPath(item) {        
    //     return item.empID  + '/workinghours';            
    // }

    constructor(props) {
        super(props);
        this.onBook = this.onBook.bind(this)

        // state = { 
        //     redirect: false
        // }
    }
    onBook(item) {
    
        // need to validate !!!!!!!!
        
        const booking = {
          custID: "cust5",
          empID: item.empID,
          bookingTime: item.startTime,
          bookingDate: item.workDate
        }
    
        console.log("booking: ", booking);
    
        axios.post('http://localhost:8080/api/booking/add', 
                booking
              )
              .then(res => //showOutput(res))
                {
                    console.log("data:", res.data);
                    
                }
                )
              .catch(err => console.error("error: ", err));
    
        // redirect to upcoming bookings page or dashboard here
        // return <Redirect to="localhost:3000/upcomingBookings"></Redirect>

        let history = createHistory();
        history.push("../upcomingBookings");
        let pathUrl = window.location.href;
        window.location.href = pathUrl;  
    }

    render() {
        return (
            <div>
                <ul>
                    {
                        this.props.list.map(item => (
                           <div>
                               <React.Fragment>
                                   <Link onClick={() => {this.onBook(item)}} className="btn btn-lg btn-info">
                                        <SearchResultsItem name={item.empID}
                                        desc={item.service}
                                        date={item.workDate}
                                        start={item.startTime}
                                        end={item.endTime}/>
                                    </Link>
                                </React.Fragment>
                            </div>
                        ))
                    }
                </ul>
            </div>
        )
    }
}

export default SearchResultsList;