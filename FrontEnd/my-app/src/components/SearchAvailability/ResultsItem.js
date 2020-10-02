import React, { Component } from 'react'
import '../../css/ResultsItem.css'

class ResultsItem extends Component {

    render() {
        return (
            <div className="resultItem">
                <h2>Employee: {this.props.name}</h2>
                <p id='desc'>Service: {this.props.desc}</p>
                <p id='date'>Date: {this.props.date}</p>
                <p id='start'>Start: {this.props.start}</p>
                <p id='end'>End: {this.props.end}</p>
            </div>
        )
    }
}

export default ResultsItem;