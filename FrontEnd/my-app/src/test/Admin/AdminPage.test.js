import React from "react";
import '@testing-library/jest-dom/extend-expect'
import AddEmployeeButton from '../../components/Admin/AddEmployee/AddEmployeeButton';
import AddServiceButton from '../../components/Admin/AddService/AddServiceButton';
import AddWorkHoursButton from '../../components/Admin/AddWorkHours/AddWorkHoursButton';
import AdminUpcomingButton from '../../components/Admin/AdminBookings/AdminUpcomingButton';
import AdminPastButton from '../../components/Admin/AdminBookings/AdminPastButton';
import AssignServiceButton from '../../components/Admin/AssignService/AssignServiceButton';
import AdminPage from '../../components/Admin/AdminPage';
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

Enzyme.configure({adapter: new Adapter()});


describe('Admin Page testing', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<AdminPage/>);
    });

    it("Should render a single <AdminPage /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it("should render Dashboard title", () => {
        const title = "Admin";
        const heading = wrapper.find('h1').at(0).text();

        expect(heading).toEqual(title);
    });

    it("should render button as Add Employee", ()=>{
        const wrapper = shallow(<AddEmployeeButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('Add Employee');
    });

    it("should render button as Add Service", ()=>{
        const wrapper = shallow(<AddServiceButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('Add Service');
    });

    it("should render button as Add Working Hours", ()=>{
        const wrapper = shallow(<AddWorkHoursButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('Add Working Hours');
    });

    it("should render button as View Past Bookings", ()=>{
        const wrapper = shallow(<AdminPastButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('View All Past Bookings');
    });

    it("should render button as View Upcoming Bookings", ()=>{
        const wrapper = shallow(<AdminUpcomingButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('View All Upcoming Bookings');
    });

    it("should render button as Assign Service", ()=>{
        const wrapper = shallow(<AssignServiceButton/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('Assign Service');
    });
})

