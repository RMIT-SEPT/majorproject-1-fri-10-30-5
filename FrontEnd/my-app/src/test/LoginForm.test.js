import React from "react";
import {shallow, mount} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import LoginForm from'../components/Login/LoginForm';
import { unstable_batchedUpdates } from "react-dom";

Enzyme.configure({adapter: new Adapter()});

describe('Profile Testing', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<LoginForm/>);

    });

    it('should render heading', () => {
        const heading = wrapper.find('h1').text();
        expect(heading).toContain("Login Form");
    });

    it('should render username label', () => {
        expect(wrapper.find('label').at(0).text()).toEqual("UserName: ");
    });

    it('should render password label', () => {
        expect(wrapper.find('label').at(1).text()).toEqual("Password: ");
    });

    it('should render user type label', () => {
        expect(wrapper.find('p').text()).toEqual("User Type:");
    });

    it('should render admin label', () => {
        expect(wrapper.find('label').at(2).text()).toEqual("Admin");
    });

    it('should render worker label', () => {
        expect(wrapper.find('label').at(3).text()).toEqual("Worker");
    });

    it('should render customer label', () => {
        expect(wrapper.find('label').at(4).text()).toEqual("Customer");
    });

    it('should check for submit button', () => {
        expect(wrapper.containsMatchingElement(<input type='submit'/>)).toBeTruthy();
    })
})