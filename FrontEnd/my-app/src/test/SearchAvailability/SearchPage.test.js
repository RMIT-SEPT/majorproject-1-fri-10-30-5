import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import ResultsList from '../../components/SearchAvailability/ResultsList'

Enzyme.configure({adapter: new Adapter()});


describe('Render <ResultList /> component', () => {

  const listProps = {
    list: {
      empID: "1",
      service: "service",
      workDate: "1/1/1111",
      startTime: "14:00",
      endTime: "15:00"
    }
  }
  
    it("Should render a single <ResultsList> component", ()=>{
        // const component = shallow(<ResultsList {... listProps}/>);
        // expect(component).toHaveLength(1);
    });
})

