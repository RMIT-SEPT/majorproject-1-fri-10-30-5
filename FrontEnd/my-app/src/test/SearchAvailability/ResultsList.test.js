import React from "react";
import {shallow, mount} from "enzyme";
import ResultsItem from '../../components/SearchAvailability/ResultsItem'
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

Enzyme.configure({adapter: new Adapter()});


describe('Render <ResultList> component', () => {
  
    it("Should render a single <ResultsItem> component", ()=>{
        const component = shallow(<ResultsItem/>);
        expect(component).toHaveLength(1);
    });
})

