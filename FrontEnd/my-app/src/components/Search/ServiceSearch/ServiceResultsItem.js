import React, { Component } from 'react'

export default class ServiceResultsItem extends Component {
  render() {
    return (
      <div>
        <h2>Employee: {this.props.name}</h2>
      </div>
    )
  }
}
