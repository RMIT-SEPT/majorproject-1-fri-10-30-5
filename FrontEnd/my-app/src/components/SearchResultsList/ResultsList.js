import React, { Component } from 'react'
import SearchResultsItem from '../ResultsItem/ResultsItem'

class SearchResultsList extends Component {

    render() {
        return (
            <div>
                <ul>
                    {
                        this.props.results.map(item => (
                            <SearchResultsItem />
                        ))
                    }
                </ul>
            </div>
        )
    }
}

export default SearchResultsList;