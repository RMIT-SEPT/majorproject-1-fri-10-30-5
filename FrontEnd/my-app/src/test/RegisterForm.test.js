import React from "react";
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

import RegisterForm from '../components/Register/RegisterForm'

Enzyme.configure({adapter: new Adapter()});


describe('Render <RegisterForm /> component', () => {
  
    it("Should render a single <RegisterForm /> component", ()=>{
        const component = shallow(<RegisterForm />);
        expect(component).toHaveLength(1);
    });
})

