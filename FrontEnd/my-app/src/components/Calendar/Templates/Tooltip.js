import React, { Component } from 'react'

export default class Tooltip extends Component {
    render() {
        return (
            <div>
                {this.props.event.heading}
            </div>
        )
    }
}
