import React from "react";
import '@testing-library/jest-dom/extend-expect'
import Navbar from '../../components/Navbar/Navbar'
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

Enzyme.configure({adapter: new Adapter()});


describe('Navbar testing when customer logged in', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<Navbar />);
        wrapper.setState({user: {username: "cus6", userType: "customer"}});
    });

    it("Should render a single <Navbar /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it("Should render Profile tab ", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('Profile');
    });

    it("Should render Dashboard tab ", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('Dashboard');
    });

    it("Should render About Us tab", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('About Us');
    });

    it("Should render Contact Us tab", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('Contact Us');
    });

    it("Should render Log Out tab", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('Log Out');
    });
})

describe('Navbar testing when admin logged in', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<Navbar />);
        wrapper.setState({user: {username: "admin6", userType: "admin"}});
    });


    it("Should render Admin tab ", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('Admin');
    });
})

describe('Navbar testing when employee logged in', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<Navbar />);
        wrapper.setState({user: {username: "emp6", userType: "employee"}});
    });


    it("Should render Worker tab ", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('Worker');
    });
})

describe('Navbar testing when logged out', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<Navbar />);
        wrapper.setState({user: {username: "guest", userType: "guest"}});
    });

    it("Should render About Us tab", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('About Us');
    });

    it("Should render Contact Us", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('Contact Us');
    });

    it("Should render Log In tab", ()=>{
        const tab = wrapper.find('ul').text()
        expect(tab).toContain('Log In');
    });
})



