import React, { Component } from 'react'
import ServiceResultsItem from './ServiceResultsItem'
import {Link} from 'react-router-dom'


class ServiceResultsList extends Component {

  render() {

    console.log("props: ", this.props.list)

    return (
      <div>
        <div className="resultsList">
          <React.Fragment>
            {this.props.list.map((item, index) => (
              <Link key={"index"} to={"/"+item+"/booking"} className="btn btn-lg btn-info">
                <ServiceResultsItem 
                name={item}
                />
              </Link>
          ))}
          </React.Fragment>
        </div>
      </div>
    )
  }
}

export default ServiceResultsList