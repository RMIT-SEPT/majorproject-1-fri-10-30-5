import React, { Component } from 'react'
import SearchResultsItem from './ResultsItem'

class SearchResultsList extends Component {

    render() {
        return (
            <div>
                <ul>
                    {
                       this.props.list.map(item => (
                            <SearchResultsItem name={item.empID}
                            desc={item.service}
                            date={item.workDate}
                            start={item.startTime}
                            end={item.endTime}/>
                        ))
                    }
                </ul>
            </div>
        )
    }
}

export default SearchResultsList;