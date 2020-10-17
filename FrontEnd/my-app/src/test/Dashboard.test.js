import React from "react";
import '@testing-library/jest-dom/extend-expect'
import SearchPageButton from '../components/Search/SearchPageButton';
import PastBookingsButton from '../components/BookingData/PastBookingsButton';
import UpcomingBookingsButton from '../components/BookingData/UpcomingBookingsButton';
import Dashboard from '../components/Dashboard';
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

Enzyme.configure({adapter: new Adapter()});


describe('Dashboard testing', () => {

    it("should render button as Make A Booking", ()=>{
        const wrapper = shallow(<SearchPageButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('Make a Booking');
    });

    it("should render button as View Past Bookings", ()=>{
        const wrapper = shallow(<PastBookingsButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('View Past Bookings');
    });

    it("should render button as View Upcoming Bookings", ()=>{
        const wrapper = shallow(<UpcomingBookingsButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('View Upcoming Bookings');
    });
})

