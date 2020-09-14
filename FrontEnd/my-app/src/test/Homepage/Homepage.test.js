import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import Homepage from '../../components/Homepage/Homepage'

Enzyme.configure({adapter: new Adapter()});


describe('Render <Homepage /> component', () => {
  
    it("Should render a single <Homepage /> component", ()=>{
        const component = shallow(<Homepage />);
        expect(component).toHaveLength(1);
    });
})

