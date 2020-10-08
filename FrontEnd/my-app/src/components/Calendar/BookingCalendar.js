import React, { Component } from 'react'
import { ScheduleComponent, Week, Inject, ViewsDirective, ViewDirective } from '@syncfusion/ej2-react-schedule';
import BookingContent from './Templates/BookingContent'
import BookingHeader from './Templates/BookingHeader'
import Event from './Templates/Event'
import Tooltip from './Templates/Tooltip'
import moment from 'moment'
import axios from 'axios'

import '../../css/WorkerCalendar.css'
import "../../../node_modules/@syncfusion/ej2-base/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-buttons/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-calendars/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-dropdowns/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-inputs/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-lists/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-navigations/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-popups/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-splitbuttons/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-react-schedule/styles/material.css";


class WorkerCalendar extends Component {

    constructor() {
        
        super()

        this.state = {
            data: [],
            empName: window.location.pathname.split('/')[1],
            booking: null,
            results: null,
            customers: [],
            existingBookings: [],
            
            user: {
                username: "cus6",
                userType: "admin"
            },

            admin: {
                customer: null
            }
        }
    }

    componentDidMount() {

        axios.get('http://localhost:8080/api/person/customer/list/')
        .then(res => {
          this.setState({
            customers: res.data,
            userName: res.data[0].userName
          })
        })
        .catch((error) => {
          console.log("error",error)
        })  

        let url
        if(this.state.userType !== "admin") {
            url = 'http://localhost:8080/api/booking/list/' + this.state.user.username
        }
        else {
            url = 'http://localhost:8080/api/booking/admin/upcoming-Bookings'
        }

        axios.get(url)
        .then(res => {
          this.setState({
            existingBookings: res.data
          }, () => {
              console.log("existing bookings: ", this.state.existingBookings)
          })
        })
        .catch((error) => {
          console.log("error",error)
        })  


        this.getHours()
    }

    eventTemplate(props) {
        return (<Event event={props}/>);
    }

    tooltipTemplate(props) {
        return (<Tooltip event={props} />);
    }

    bookingHeaderTemplate() {
        return (<BookingHeader />);
    }

    // The booking object is created in here
    bookingContentTemplate(props) {

        return (<BookingContent 
            booking={this.state.booking}
            service={this.state.service}
        />);
    }

    getHours() {

        const url = 'http://localhost:8080/api/workinghours/list/' + this.state.empName
        axios.get(url, {})
        .then(res => {
            this.setState({ results: res.data }, () => {
                this.parseData()
            })
        })
        .catch((error) => {
            console.log("error",error)
        })
    }

    // parse data from the backend into data that
    // can be represented and injected into the worker calendar
    parseData() {

        let data = [];

        let date, year, month, day, startTime, endTime
        for(let i = 0; i < Object.keys(this.state.results).length; i++) {

            date = moment(this.state.results[i]["workDate"], "YYYY-MM-DD")
            year = parseInt(date.format("YYYY"))
            month = parseInt(date.format("MM")) - 1
            day = parseInt(date.format("DD"))

            if(this.state.results[i]["startTime"] < 1000) {
                startTime = moment(this.state.results[i]["startTime"], "Hmm")
                endTime = moment(this.state.results[i]["endTime"], "Hmm")

            } else {
                startTime = moment(this.state.results[i]["startTime"], "HHmm")
                endTime = moment(this.state.results[i]["endTime"], "HHmm")
            }

            // check if this booking is an existing booking
            let booked = false
            for(let i = 0; i < this.state.existingBookings.length; i++) {

                // check if same customer

                // check if same emp

                // check if same date

                // check if same time
            }
            
            data.push({
                    id: i,
                    heading: 'Available',
                    Subject: this.state.results[i]["service"],
                    StartTime: new Date(year, month, day, parseInt(startTime.format('HH')), parseInt(startTime.format('mm'))),
                    EndTime: new Date(year, month, day, parseInt(endTime.format('HH')), parseInt(endTime.format('mm'))),
                    IsBlock: booked
            })
        }
        
        // test results
        this.setState({ data: data }, () => { console.log(this.state.data) })

    }

    onEventClick(args) {
        let event = this.scheduleObj.getEventDetails(args.element);

        let custID
        let booked = false

        if(this.state.user.userType === "admin") {
            custID = this.state.admin.customer
        }
        else {
            custID = this.state.user.username
        }        

        this.setState({
            booking: {
                custID: custID,
                empID: this.state.empName,
                bookingTime: moment(event.StartTime).format("hhmm"),
                bookingDate: moment(event.StartTime).format("yyyy-MM-DD")
            },

            service: event.Subject
        }, () => {
            console.log("Booking: ", this.state.booking)
        })
    }

    onSelectCustomer = event => {
        this.setState({admin: {
            customer: event.target.value
        }})
      };

    get renderAdminPanel() {

        let res
        
        if(this.state.user.userType === "admin") {
            
            res = (
                <div>
                    <h2>{this.state.empName}</h2>
                    <p>Select a customer, and any available time slot to confirm and create a booking with this worker.</p>
                    <div className="drop-down">
                        <h5>Customer:</h5>
                        <select name = "userName"  onChange = {this.onSelectCustomer}>{
                            this.state.customers.map((cust,index) => <option key={index} value={cust.userName} >{cust.userName}  </option>)}
                        </select>
                        <br></br>
                        <br></br>
                    </div>
                </div>
            )
        }
        else {

            res = (
                <div>
                    <h2>{this.state.empName}</h2>
                    <p>Select any available time slot to confirm and create a booking with this worker.</p>
                </div>
            )
        }

        return res
    }

    render() {

        return (
            <div>
                {this.renderAdminPanel}
                <ScheduleComponent 
                ref={t => this.scheduleObj = t}
                eventClick={this.onEventClick.bind(this)}
                height='650px' 
                selectedDate={new Date()} 
                firstDayOfWeek={new Date().getDay()}
                showHeaderBar={false}
                timeScale={{enable: true, interval: 60, slotCount: 1}}
                startHour='08:00'
                endHour='21:00'
                readonly={true}
                quickInfoTemplates={{ 
                    header: this.bookingHeaderTemplate.bind(this), 
                    content: this.bookingContentTemplate.bind(this)
                }}
                eventSettings={{
                    dataSource: this.state.data, 
                    template: this.eventTemplate.bind(this),
                    enableTooltip:true,
                    tooltipTemplate: this.tooltipTemplate.bind(this),
                }}
                >
                    <ViewsDirective>
                        <ViewDirective option='Week' />
                    </ViewsDirective>
                    <Inject services={[Week]}/>
                </ScheduleComponent>
            </div>
        );
    }
}

export default WorkerCalendar