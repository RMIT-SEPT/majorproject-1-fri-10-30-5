import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import AddWorkHours from '../../../components/Admin/AddWorkHours/AddWorkHours'

Enzyme.configure({adapter: new Adapter()});


describe('Render <AddWorkHours /> component', () => {

    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<AddWorkHours />);
    })
  
    it("Should render a single <AddWorkHours /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render heading', () => {
        expect(wrapper.find('h3').text()).toEqual("Add Working Hours");
    });

    it('should render employee id label', () => {
        expect(wrapper.find('label').at(0).text()).toEqual("Employee");
    }); 

    it('should render start time label', () => {
        expect(wrapper.find('label').at(1).text()).toEqual("Start time");
    }); 

    it('should render end time label', () => {
        expect(wrapper.find('label').at(2).text()).toEqual("End time");
    });

    it('should render date label', () => {
        expect(wrapper.find('label').at(3).text()).toEqual("Date");
    });

    it('should render service label', () => {
        expect(wrapper.find('label').at(4).text()).toEqual("Service");
    });
})

