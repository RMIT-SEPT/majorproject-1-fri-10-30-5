import React, { Component } from 'react'
import './ResultsItem.css'

class ResultsItem extends Component {
    render() {
        return (
            <li>
                <h2>{this.props.name}</h2>
                <p id='desc'>{this.props.desc}</p>
            </li>
        )
    }
}

export default ResultsItem;