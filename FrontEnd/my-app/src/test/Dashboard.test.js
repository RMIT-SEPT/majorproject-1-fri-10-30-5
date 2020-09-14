import React from "react";
import SearchPageButton from '../components/SearchAvailability/SearchPageButton';
import PastBookingsButton from '../components/Bookings/PastBookingsButton';
import UpcomingBookingsButton from '../components/Bookings/UpcomingBookingsButton';
import Dashboard from '../components/Dashboard';
import {shallow, mount} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import AddEmployeeButton from "../components/AddEmployee/AddEmployeeButton";

Enzyme.configure({adapter: new Adapter()});


describe('Dashboard testing', () => {
    it("should render title", () => {
        const dashboardProps = {
            user: {
                username: 'cus6',
                userType: 'customer'
            }
        }
        const wrapper = shallow(<Dashboard {...dashboardProps} />);
        const title = "Dashboard";
        const heading = wrapper.find('h1').text();
        expect(heading).toEqual(title);
    });

    it("should render button as Make A Booking", ()=>{
        const wrapper = shallow(<SearchPageButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('Make a Booking');
    });

    it("should render button as View Profile", ()=>{
        const dashboardProps = {
            user: {
                username: 'cus6',
                userType: 'customer'
            }
        }
        const wrapper = shallow(<Dashboard {...dashboardProps}/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('Profile');
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

    it("should render Add Employee Button", ()=> {

        const wrapper = shallow(<AddEmployeeButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('Add Employee');

    });




})

