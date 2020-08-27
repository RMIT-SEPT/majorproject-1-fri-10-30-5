import React, { Component } from 'react'
import ResultsItem from '../ResultsItem/ResultsItem'

class ResultsList extends Component {

    render() {

        return (
            <div>
                <ul>
                    {
                        
                        Object.keys(this.props.results).map((item) => (

                            <ResultsItem 
                                name = { this.props.results[item]['businessName'] }
                                desc = { this.props.results[item]['desc'] }
                            />

                        ))
                    }
                </ul>
            </div>
        )
    }
}

export default ResultsList;