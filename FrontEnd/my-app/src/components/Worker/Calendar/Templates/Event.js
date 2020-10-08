import React, { Component } from 'react'
import moment from 'moment'

export default class Event extends Component {

    isBlocked(b) {

        if(b) {
            return (
                <div className="template-wrap-blocked">
                    <div className="time">
                    {moment(this.props.event.StartTime).format("h:mm A")} - {moment(this.props.event.EndTime).format("h:mm A")}</div>
                    <div className="heading">{this.props.event.Subject}</div>
                </div>
            )
        }
        else {
            return (
                <div className="template-wrap">
                    <div className="time">
                    {moment(this.props.event.StartTime).format("h:mm A")} - {moment(this.props.event.EndTime).format("h:mm A")}</div>
                    <div className="heading">{this.props.event.Subject}</div>
                </div>
            )
        }
    }
    render() {
        return (
            this.isBlocked(this.props.event.IsBlock)
        )
    }
}   
