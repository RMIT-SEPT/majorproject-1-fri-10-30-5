import React, { Component } from 'react'
import SearchResultsItem from './ResultsItem'
import {Link} from 'react-router-dom'

class SearchResultsList extends Component {

    findPath(item) {        
        return item.empID  + '/workinghours';            
    }

    render() {
        return (
            <div>
                <ul>
                    {
                        this.props.list.map(item => (
                           <div>
                               <React.Fragment>
                                   <Link to= {this.findPath(item)} className="btn btn-lg btn-info">
                                        <SearchResultsItem name={item.empID}
                                        desc={item.service}
                                        date={item.workDate}
                                        start={item.startTime}
                                        end={item.endTime}/>
                                    </Link>
                                </React.Fragment>
                            </div>
                        ))
                    }
                </ul>
            </div>
        )
    }
}

export default SearchResultsList;