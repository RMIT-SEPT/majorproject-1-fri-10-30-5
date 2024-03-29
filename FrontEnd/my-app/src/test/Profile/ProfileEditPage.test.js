import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import ProfileEditPage from '../../components/Profile/ProfileEditPage'

Enzyme.configure({adapter: new Adapter()});


describe('Render <ProfileEditPage /> component', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<ProfileEditPage/>);

    });
  
    it("Should render a single <ProfileEditPage /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render heading', () => {
        const heading = wrapper.find('h3').text();
        expect(heading).toContain("Update Details Form");
    });

    it('should render first name label', () => {
        expect(wrapper.find('label').at(0).text()).toEqual("First Name");
    }); 

    it('should render last name label', () => {
        expect(wrapper.find('label').at(1).text()).toEqual("Last Name");
    }); 

    it('should render address label', () => {
        expect(wrapper.find('label').at(2).text()).toEqual("Address");
    });

    it('should render phone label', () => {
        expect(wrapper.find('label').at(3).text()).toEqual("Phone");
    });

    it('should render username label', () => {
        expect(wrapper.find('label').at(4).text()).toEqual("Username");
    });

    it('should render password label', () => {
        expect(wrapper.find('label').at(5).text()).toEqual("Password");
    });

    it('should render confirm password label', () => {
        expect(wrapper.find('label').at(6).text()).toEqual("Confirm Password");
    });

    it('should check for submit button', () => {
        expect(wrapper.containsMatchingElement(<input type='submit'/>)).toBeTruthy();
    })
})