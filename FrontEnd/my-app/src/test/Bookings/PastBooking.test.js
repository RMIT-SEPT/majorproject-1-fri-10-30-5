import React from "react";
import {shallow, mount} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import PastBooking from '../../components/BookingData/PastBookings.js';

Enzyme.configure({adapter: new Adapter()});

describe('Past Booking Testing', () => {
    let wrapper;
    beforeEach(() => {
        const userProps = {
            user: {
                username: 'cus6',
                userType: 'customer'
            }
        }
        wrapper = shallow(<PastBooking {... userProps} />);

    })

    it("Should render a single <PastBooking /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render page title', () => {
        const title = "Past Bookings";
        const pageHeading = wrapper.find('h5').at(0).text();
        expect(pageHeading).toEqual(title);
    });


    it('should render message if no bookings', () => {
        const userProps = {
            user: {
                username: 'cus5',
                userType: 'customer'
            }
        }
        wrapper = shallow(<PastBooking {... userProps} />);
        expect(wrapper.find('h5').at(1).text()).toEqual("You have no past bookings.");
    });
})
