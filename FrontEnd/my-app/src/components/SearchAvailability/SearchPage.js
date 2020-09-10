import React, { Component } from 'react'
import ResultsList from './ResultsList'

class SearchPage extends Component {


  render() {

    const results = {
      0: {
        businessName: "A1 Cuts",
        desc: "Upscale hair salon offering a wide range of services that include cutting, perming, colouring, blow drying, etc.",
        },
      1: {
        businessName: "John's Garage",
        desc: "If you're looking for quality automotive repairs a step above the rest, come to John's Garage!"
      },
      2: {
        businessName: "Oz Garden Services",
        desc: "Call Oz Garden Services Melbourne to come over and take care of your garden & home maintenance."
      }
    }

    return (
      <div>
        <ResultsList results={ results }/>
      </div>
    )
  }
}

export default SearchPage;
