import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import ResultsItem from '../../../components/SearchAvailability/ResultsItem'

Enzyme.configure({adapter: new Adapter()});


describe('Render <ResultsItem /> component', () => {
  
    it("Should render a single <ResultsItem /> component", ()=>{
        const component = shallow(<ResultsItem />);
        expect(component).toHaveLength(1);
    });
})

