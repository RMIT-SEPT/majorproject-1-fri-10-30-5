import React, { Component } from 'react'
import { ScheduleComponent, Week, Inject, ViewsDirective, ViewDirective } from '@syncfusion/ej2-react-schedule';
import moment from 'moment'
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

const createHistory = require("history").createBrowserHistory;

class WorkerCalendar extends Component {

    constructor() {
        
        super()

        this.state = {
            data: []
        }
    }

    componentDidMount() {
        this.parseData()
    }

    // parse data from the backend into data that
    // can be represented and injected into the worker calendar
    parseData() {

        let data = [];

        // parse data here
        
        // test results
        this.setState({
            data: [
                {
                    id: 1,
                    heading: 'Available',
                    Subject: 'wash',
                    StartTime: new Date(2020, 9, 6, 10, 0),
                    EndTime: new Date(2020, 9, 6, 12, 0),
                },
                {
                    id: 2,
                    heading: 'Available',
                    Subject: 'wash',
                    StartTime: new Date(2020, 9, 4, 15, 0),
                    EndTime: new Date(2020, 9, 4, 17, 0),
                },
                {
                    id: 3,
                    heading: 'Available',
                    Subject: 'wash',
                    StartTime: new Date(2020, 9, 3, 11, 0),
                    EndTime: new Date(2020, 9, 3, 13, 0),
                },
            ]   
        }, 
        () => {
            console.log("data: ", this.state.data)
        })

    }

    eventTemplate(props) {
        return (
            <div className="template-wrap">
                <div className="time">
                {moment(props.StartTime).format("h:mm A")} - {moment(props.EndTime).format("h:mm A")}</div>
                <div className="heading">{props.Subject}</div>
            </div>)
        ;
    }

    tooltipTemplate(props) {
        return (
            <div>
                <div>{props.heading}</div>
            </div>
        );
    }

    header(props) {
        return (<div></div>);
    }

    onBook = e => {

        e.preventDefault()

        console.log("booking!")

        // book this timeslot

        // redirect 
        let history = createHistory();
        history.push("../upcomingBookings");
        let pathUrl = window.location.href;
        window.location.href = pathUrl;  
    }

    content(props) {
        return (
            <form onSubmit={this.onBook}>
                <button type='submit'>Book now</button>
            </form>
        );
    }

    render() {

        return (
            <div>
                <h2>{window.location.pathname.split('/')[1]}</h2>
                <p>Select any available time slot to confirm and create a booking with this worker.</p>
                <ScheduleComponent 
                height='550px' 
                selectedDate={new Date()} 
                firstDayOfWeek={new Date().getDay()}
                showHeaderBar={false}
                timeScale={{enable: true, interval: 60, slotCount: 1}}
                startHour='08:00'
                endHour='21:00'
                readonly={true}
                quickInfoTemplates={{ header: this.header.bind(this), content: this.content.bind(this)}}
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