import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import ResultsList from '../../components/SearchAvailability/ResultsList'

Enzyme.configure({adapter: new Adapter()});


describe('Render <ResultList /> component', () => {

  
    // it("Should render a single <ResultsList> component", ()=>{
    //     const component = shallow(<ResultsList/>);
    //     expect(component).toHaveLength(1);
    // });
})

