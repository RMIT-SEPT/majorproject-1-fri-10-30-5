import React, { Component } from 'react'

class PastBookingsItem extends Component {
    render() {
        return (
            <tr>
            <td id = 'empID'>{this.props.empId}</td>
            <td id='date'>{this.props.date}</td>
            <td id='time'>{this.props.time}</td>
            <td id='status'>{this.props.status}</td>
            </tr>
        )
    }
}

export default PastBookingsItem;
