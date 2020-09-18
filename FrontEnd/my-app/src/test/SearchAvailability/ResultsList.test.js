import React from "react";
import {shallow, mount} from "enzyme";
import ResultsItem from '../../components/SearchAvailability/ResultsItem'
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

Enzyme.configure({adapter: new Adapter()});


describe('Render <ResultList> component', () => {

    const props = {
        name:"emp1",
        desc:"moving",
        workDate:"2020-08-09",
        startTime:"1400",
        endTime:"1600"
    }
  
    it("should render a single <ResultsItem> component", ()=>{
        const component = shallow(<ResultsItem/>);
        expect(component).toHaveLength(1);
    });

    it("should render item with correct values", () => {
        const component = shallow(
            <ResultsItem 
                name={props.name}
            />
        );
        expect(component.find('h2').text()).toEqual("Employee: emp1");
    });

    it("should render item with correct values", () => {
        const component = shallow(
            <ResultsItem 
                desc={props.desc}
            />
        );
        expect(component.find('#desc').text()).toEqual("Service: moving");
    });

    it("should render item with correct values", () => {
        const component = shallow(
            <ResultsItem 
                date={props.workDate}
            />
        );
        expect(component.find('#date').text()).toEqual("Date: 2020-08-09");
    });

    it("should render item with correct values", () => {
        const component = shallow(
            <ResultsItem 
                start={props.startTime}
            />
        );
        expect(component.find('#start').text()).toEqual("Start: 1400");
    });

    it("should render item with correct values", () => {
        const component = shallow(
            <ResultsItem 
                end={props.endTime}
            />
        );
        expect(component.find('#end').text()).toEqual("End: 1600");
    });
})

