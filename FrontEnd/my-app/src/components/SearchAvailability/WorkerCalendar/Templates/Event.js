import React, { Component } from 'react'
import moment from 'moment'

export default class Event extends Component {
    render() {
        return (
            <div className="template-wrap">
                 <div className="time">
                {moment(this.props.event.StartTime).format("h:mm A")} - {moment(this.props.event.EndTime).format("h:mm A")}</div>
                <div className="heading">{this.props.event.Subject}</div>
            </div>
        )
    }
}   
