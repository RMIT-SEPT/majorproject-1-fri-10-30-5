import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import AssignService from '../../../components/Admin/AssignService/AssignService'

Enzyme.configure({adapter: new Adapter()});


describe('Render <AssignService /> component', () => {

    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<AssignService />);
    })
  
    it("Should render a single <AssignService /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it('should render heading', () => {
        expect(wrapper.find('h3').text()).toEqual("Assign Service");
    });

    it('should render employee label', () => {
        expect(wrapper.find('label').at(0).text()).toEqual("Employee:");
    }); 

    it('should render service name label', () => {
        expect(wrapper.find('label').at(1).text()).toEqual("Service name:");
    }); 
})

