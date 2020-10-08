import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import AddEmployee from '../../../components/Admin/AddEmployee/AddEmployee'

Enzyme.configure({adapter: new Adapter()});


describe('Render <AddEmployee /> component', () => {

    let wrapper;
    beforeEach(() => {
        const adminProps = {
            user: {
                username: 'admin1',
                userType: 'admin'
            }
        }
        wrapper = shallow(<AddEmployee {... adminProps} />);
    })
  
    it("Should render a single <AddEmployee /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render heading', () => {
        expect(wrapper.find('h3').text()).toEqual("Add New Employee");
    });

    it('should render first name label', () => {
        expect(wrapper.find('label').at(0).text()).toEqual("First name");
    }); 

    it('should render last name label', () => {
        expect(wrapper.find('label').at(1).text()).toEqual("Last name");
    }); 

    it('should render address label', () => {
        expect(wrapper.find('label').at(2).text()).toEqual("Username");
    });

    it('should render phone label', () => {
        expect(wrapper.find('label').at(3).text()).toEqual("Address");
    });

    it('should render username label', () => {
        expect(wrapper.find('label').at(4).text()).toEqual("Phone");
    });

    it('should render password label', () => {
        expect(wrapper.find('label').at(5).text()).toEqual("Password");
    });
})

