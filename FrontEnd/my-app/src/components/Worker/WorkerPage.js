import React, { Component } from 'react'
import { authenticate } from '../../actions/auth'
import Roster from './Calendar/Roster'

export default class WorkerPage extends Component {

  constructor() {
    super()

    this.state = {
      user: authenticate()
    }
  }

  render() {
    return (
      <div>
        <Roster empID={this.props.match.params.empId} />
      </div>
    )
  }
}
