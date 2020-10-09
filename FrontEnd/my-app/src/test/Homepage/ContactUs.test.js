import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import ContactUs from '../../components/Homepage/ContactUs'

Enzyme.configure({adapter: new Adapter()});


describe('Render <ContactUs /> component', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<ContactUs />);

    })
  
    it("Should render a single <ContactUs /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render heading', () => {
        expect(wrapper.find('h3').text()).toEqual("Contact Us");
    });

    it('should render name field', () => {
        expect(wrapper.find('label').at(0).text()).toEqual("Name");
    });

    it('should render email field', () => {
        expect(wrapper.find('label').at(1).text()).toEqual("Email address");
    });

    it('should render message field', () => {
        expect(wrapper.find('label').at(2).text()).toEqual("Message");
    });
}) 