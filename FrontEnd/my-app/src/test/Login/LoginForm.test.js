import React from "react";
import {shallow, mount} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import LoginForm from'../../components/Login/LoginForm';
import { unstable_batchedUpdates } from "react-dom";

Enzyme.configure({adapter: new Adapter()});

describe('Profile Testing', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<LoginForm/>);

    });

    it('should render heading', () => {
        const heading = wrapper.find('h3').text();
        expect(heading).toContain("Login");
    }); 

    it('should render username label', () => {
        expect(wrapper.find('label').at(0).text()).toEqual("Username ");
    });

    it('should render password label', () => {
        expect(wrapper.find('label').at(1).text()).toEqual("Password ");
    });
})