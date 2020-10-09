import React from "react";
import '@testing-library/jest-dom/extend-expect'
import SearchPageButton from '../components/SearchAvailability/SearchPageButton';
import PastBookingsButton from '../components/Bookings/PastBookingsButton';
import UpcomingBookingsButton from '../components/Bookings/UpcomingBookingsButton';
import Dashboard from '../components/Dashboard';
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

Enzyme.configure({adapter: new Adapter()});


describe('Dashboard testing', () => {
    let wrapper;
    beforeEach(() => {
        const dashboardProps = {
            user: {
                username: 'cus6',
                userType: 'customer'
            }
        }
        wrapper = shallow(<Dashboard {...dashboardProps} />);
    });

    it("Should render a single <Dashboard /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it("should render Dashboard title", () => {
        const title = "Dashboard";
        const heading = wrapper.find('h1').at(0).text();

        expect(heading).toEqual(title);
    });

    it("should render Welcome with username", () => {
        const welcome = wrapper.find('h1').at(1).text();

        expect(welcome).toEqual("Welcome, cus6!");
    });

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

