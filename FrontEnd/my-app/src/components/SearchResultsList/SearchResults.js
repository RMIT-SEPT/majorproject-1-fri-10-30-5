import React, { Component } from 'react'
import SearchResultsItem from '../SearchResultsItem/SearchResultsItem'

class SearchResultsList extends Component {
    render() {
        return (
            <div>
                <ul>
                    <SearchResultsItem 
                        resultName={'A1 Cuts'}
                        description={'Upscale hair salon offering a wide range of services that include cutting, perming, colouring, blow drying, etc.'}
                        ></SearchResultsItem>
                    <SearchResultsItem 
                        resultName={'John\'s Garage'}
                        description={'If you\'re looking for quality automotive repairs a step above the rest, come to John\'s Garage!'}
                        ></SearchResultsItem>
                </ul>
            </div>
        )
    }
}

export default SearchResultsList;