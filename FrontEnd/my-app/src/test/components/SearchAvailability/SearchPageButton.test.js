import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import Sinon from 'sinon';

import SearchPageButton from '../../../components/SearchAvailability/SearchPageButton'

Enzyme.configure({adapter: new Adapter()});


describe('Render <SearchPageButton /> component', () => {
  
    it("render a single <SearchPageButton /> component", ()=>{
        const component = shallow(<SearchPageButton />);
        expect(component).toHaveLength(1);
    });

    it("simulate click event", ()=>{
        const onButtonClick = Sinon.spy();
        const component = shallow(<SearchPageButton onButtonClick={onButtonClick} />);
        component.simulate('click');
        expect(onButtonClick).toHaveProperty('callCount', 1);
    });
})

