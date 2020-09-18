import React from "react";
import {shallow, mount} from "enzyme";
import { Route } from 'react-router';
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import Profile from '../../components/Profile/Profile';
import App from '../../App';
Enzyme.configure({adapter: new Adapter()});

describe('Profile Testing', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<Profile/>);

    });

    it(' should render path containing username', () => {
        wrapper = shallow(<App/>);
        wrapper.setState({ username: 'cus6'});
        const pathMap = wrapper.find(Route).reduce((pathMap, route) => {
            const routeProps = route.props();
            if (routeProps.component) {
                pathMap[routeProps.path] = routeProps.component;
            } else if (routeProps.render) {
                pathMap[routeProps.path] = routeProps.render({}).type;
            }

            return pathMap;
        }, {});

        expect(pathMap['profile/cus6']).toBe(Profile.path);
    });

    it('should render title with username', () => {
        wrapper.setProps({ username: 'mary09' })
        const heading = wrapper.find('h1').text();
        expect(heading).toEqual("Profile for mary09");
    });

    it('should render name field', () => {
        wrapper.setProps({ name: 'Mary Jones' })
        expect(wrapper.find('li').at(0).text()).toEqual("Name: Mary Jones");
    });

    it('should render phone field', () => {
        wrapper.setProps({ phone: '0123456' })
        expect(wrapper.find('li').at(1).text()).toEqual("Phone: 0123456");
    });

    it('should render address field', () => {
        wrapper.setProps({ address: '9 Hollow Drive' })
        expect(wrapper.find('li').at(2).text()).toEqual("Address: 9 Hollow Drive");
    });

    it('should render Profile Edit button', () => {
        const userProps = {
            user: {
                username: 'cus6',
                userType: 'customer'
            }
        }
        wrapper = shallow(<Profile {...userProps}/>);
        const buttonName = wrapper.find('Link').first().text();
        expect(buttonName).toEqual('Edit Details');
    });
})