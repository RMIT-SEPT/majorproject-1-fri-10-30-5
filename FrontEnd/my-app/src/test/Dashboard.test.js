import React from "react";
import SearchPageButton from './SearchPage/SearchPageButton';
import ProfileButton from '../components/Profile/ProfileButton';
import PastBookingsButton from '../components/Bookings/PastBookingsButton';
import UpcomingBookingsButton from '../components/Bookings/UpcomingBookingsButton';
import {shallow, mount} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

Enzyme.configure({adapter: new Adapter()});


describe('render Dashboard buttons', () => {
    it("should render button as Make A Booking", ()=>{
        const wrapper = shallow(<SearchPageButton/>);
        const pageHeading = wrapper.find('Link').first().text();
        expect(pageHeading).toEqual('Make a Booking');
    });

    it("should render button as View Profile", ()=>{
        const wrapper = shallow(<ProfileButton/>);
        const pageHeading = wrapper.find('Link').first().text();
        expect(pageHeading).toEqual('View Profile');
    });

    it("should render button as View Profile", ()=>{
        const wrapper = shallow(<ProfileButton/>);
        const pageHeading = wrapper.find('Link').first().text();
        expect(pageHeading).toEqual('View Profile');
    });

    it("should render button as View Past Bookings", ()=>{
        const wrapper = shallow(<PastBookingsButton/>);
        const pageHeading = wrapper.find('Link').first().text();
        expect(pageHeading).toEqual('View Past Bookings');
    });

    it("should render button as View Upcoming Bookings", ()=>{
        const wrapper = shallow(<UpcomingBookingsButton/>);
        const pageHeading = wrapper.find('Link').first().text();
        expect(pageHeading).toEqual('View Upcoming Bookings');
    })







    //test log console - one way of testing console.log(components)
})

