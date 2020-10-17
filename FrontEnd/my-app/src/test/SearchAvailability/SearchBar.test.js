import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import Searchbar from '../../components/Search/Searchbar'

Enzyme.configure({adapter: new Adapter()});


describe('Render <Searchbar /> component', () => {
  
    it("Should render a single <Searchbar /> component", ()=>{
        const component = shallow(<Searchbar />);
        expect(component).toHaveLength(1);
    });
})

