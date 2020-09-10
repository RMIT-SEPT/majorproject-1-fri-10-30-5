import React from "react";
import {shallow, mount} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import UpcomingBookings from "../../../components/Bookings/UpcomingBookings.js";

Enzyme.configure({adapter: new Adapter()});

describe('Upcoming Booking Testing', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<UpcomingBookings/>);

    })

    it('should render page title', () => {
        const title = "Upcoming Bookings";
        const pageHeading = wrapper.find('h5').text();
        expect(pageHeading).toEqual(title);
    });

    it('should render booking employee field', () => {
        expect(wrapper.find("#empID").first().text()).toContain("Employee ID:");
    });

    it('should render booking service field', () => {
        expect(wrapper.find("#serviceID").first().text()).toContain("Service ID:");
    });

    it('should render booking date field', () => {
        expect(wrapper.find("#serviceID").first().text()).toContain("Service ID:");
    });

    it('should render booking time field', () => {
        expect(wrapper.find("#time").first().text()).toContain("Time:");
    });

    it('should render booking status field', () => {
        expect(wrapper.find("#status").first().text()).toContain("Status:");
    });
})
