import React, { Component } from 'react'
import './SearchResultsItem.css'

class SearchResultsItem extends Component {
    render() {
        return (
            <li>
                <h2>{this.props.resultName}</h2>
                <p id='desc'>{this.props.description}</p>
            </li>
        )
    }
}

export default SearchResultsItem;