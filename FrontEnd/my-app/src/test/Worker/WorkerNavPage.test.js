import React from "react";
import '@testing-library/jest-dom/extend-expect'
import {shallow} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import WorkerPage from '../../components/Worker/WorkerNavPage'

Enzyme.configure({adapter: new Adapter()});


describe('Admin Page testing', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<WorkerPage/>);
    });

    it("Should render a single <WorkerPage /> component", ()=>{
        expect(wrapper).toHaveLength(1);
    });

    it("should render Dashboard title", () => {
        const title = "Worker";
        const heading = wrapper.find('h1').at(0).text();

        expect(heading).toEqual(title);
    });
})