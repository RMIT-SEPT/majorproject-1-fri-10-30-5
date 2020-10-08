import React, { Component } from 'react'
import ServiceResultsItem from './ServiceSearch/ServiceResultsItem'
import {Link} from 'react-router-dom'

class WorkerResultsItem extends Component {
    render() {
        return (
            <div>
            <React.Fragment>
              <Link key={"index"} to={"/"+this.props.name+"/booking"} className="btn btn-lg btn-info">
                <ServiceResultsItem 
                    name={this.props.name}
                />
              </Link>
          </React.Fragment>
            </div>
        )
    }
}

export default WorkerResultsItem