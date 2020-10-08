import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import AboutUs from '../../components/Homepage/AboutUs'

Enzyme.configure({adapter: new Adapter()});


describe('Render <Homepage /> component', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<AboutUs />);

    })
  
    it("Should render a single <AboutUs /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render group name', () => {

        expect(wrapper.find('h1').text()).toEqual("SEPT Friday 10:30 AM Group 5");
    });

    it('should render member names', () => {
        expect(wrapper.find('li').at(0).text()).toContain("ALONTO");
        expect(wrapper.find('li').at(1).text()).toContain("BALASURIYA");
        expect(wrapper.find('li').at(2).text()).toContain("BITETTO");
        expect(wrapper.find('li').at(3).text()).toContain("KARUNA");
        expect(wrapper.find('li').at(4).text()).toContain("WISIDAGAMA");
    });
}) 

