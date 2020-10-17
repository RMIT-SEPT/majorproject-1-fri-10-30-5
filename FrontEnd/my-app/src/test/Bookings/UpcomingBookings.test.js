import React from "react";
import {shallow, mount} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import UpcomingBookings from "../../components/BookingData/UpcomingBookings.js";

Enzyme.configure({adapter: new Adapter()});

describe('Upcoming Booking Testing', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<UpcomingBookings/>);

    })

    it("Should render a single <Dashboard /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render page title', () => {
        const title = "Upcoming Bookings";
        const pageHeading = wrapper.find('h5').at(0).text();
        expect(pageHeading).toEqual(title);
    });
})
