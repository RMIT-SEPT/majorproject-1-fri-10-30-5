import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import RegisterForm from '../../components/Register/RegisterForm'

Enzyme.configure({adapter: new Adapter()});


describe('Render <RegisterForm /> component', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<RegisterForm />);

    })
  
    it("Should render a single <RegisterForm /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render heading', () => {
        expect(wrapper.find('h3').text()).toEqual("Sign Up");
    });

    it('should render account type field', () => {
        expect(wrapper.find('label').at(0).text()).toEqual("Account Type:");
    });

    it('should render empty as inital account type option', () => {
        expect(wrapper.find('option').at(0).text()).toEqual(" ");
    });

    it('should render  customer as account type option', () => {
        expect(wrapper.find('option').at(1).text()).toEqual("Customer");
    });

    it('should render employee as account type option', () => {
        expect(wrapper.find('option').at(2).text()).toEqual("Employee");
    });

    it('should render admin as account type option', () => {
        expect(wrapper.find('option').at(3).text()).toEqual("Admin");
    });

    it('should render first name field', () => {
        expect(wrapper.find('label').at(1).text()).toEqual("First name");
    });

    it('should render last name field', () => {
        expect(wrapper.find('label').at(2).text()).toEqual("Last name");
    });

    it('should render address field', () => {
        expect(wrapper.find('label').at(3).text()).toEqual("Address");
    });

    it('should render phone field', () => {
        expect(wrapper.find('label').at(4).text()).toEqual("Phone");
    });

    it('should render username field', () => {
        expect(wrapper.find('label').at(5).text()).toEqual("Username");
    });

    it('should render password field', () => {
        expect(wrapper.find('label').at(6).text()).toEqual("Password");
    });

    it('should render confirm password field', () => {
        expect(wrapper.find('label').at(7).text()).toEqual("Confirm Password");
    });



})

