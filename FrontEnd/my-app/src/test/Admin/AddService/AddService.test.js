import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import AddService from '../../../components/Admin/AddService/AddService'

Enzyme.configure({adapter: new Adapter()});


describe('Render <AddService /> component', () => {

    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<AddService />);
    })
  
    it("Should render a single <AddService /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render heading', () => {
        expect(wrapper.find('h3').text()).toEqual("Add Service");
    });

    it('should render service id label', () => {
        expect(wrapper.find('label').at(0).text()).toEqual("Service ID");
    }); 

    it('should render service name label', () => {
        expect(wrapper.find('label').at(1).text()).toEqual("Service name");
    }); 

    it('should render description label', () => {
        expect(wrapper.find('label').at(2).text()).toEqual("Description");
    });

    it('should render duration label', () => {
        expect(wrapper.find('label').at(3).text()).toEqual("Duration (mins)");
    });
})

