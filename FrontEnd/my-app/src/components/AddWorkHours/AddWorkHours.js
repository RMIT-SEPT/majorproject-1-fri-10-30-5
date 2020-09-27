import React, { Component } from 'react';
import axios from "axios";
import Moment from 'moment';

export default class AddWorkHours extends Component {
    constructor(props) {
        super(props);

        this.state = {
            empID: '',
            startTime: '',
            endTime:'',
            workDate:'',
            service:'',
        };
    }

    myChangeHandler = event => {
        this.setState({[event.target.name]: event.target.value})
    };

    mySubmitHandler = event => {
        event.preventDefault();
    
        const workHours = {
            empID:this.state.empID,
            startTime:this.state.startTime.replace(/:/g, ''),
            endTime:this.state.endTime.replace(/:/g, ''),
            workDate:this.state.workDate,
            service:this.state.service
        }

        console.log(workHours);
        axios.post('http://localhost:8080/api/workinghours/add', 
            workHours
          )
          .then(res => 
          {console.log(res);
          console.log(res.data);})
          .catch(err => console.error(err));
    }
    
    render() {

        return (
            <div id="addWorkHoursForm">
                <h1>Add Working Hours</h1>
                <form onSubmit={this.mySubmitHandler}>
                    <div className='form-group'>
                    <label htmlFor="empID">Employee ID</label>
                    <input name="empID" type='text' value= {this.state.empID} onChange={this.myChangeHandler}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="startTime">Start Time</label>
                    <input name="startTime" type='time' value= {this.state.startTime} onChange={this.myChangeHandler}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="endTime">End Time</label>
                    <input name="endTime" type='time' value= {this.state.endTime} onChange={this.myChangeHandler}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="workDate">Date</label>
                    <input name="workDate" type='date' value= {Moment(this.state.workDate).format('YYYY-MM-DD')} onChange={this.myChangeHandler}/>
                    </div>
                    <br></br>
                    <div className='form-group'>
                    <label htmlFor="service">Service</label>
                    <input name="service" type='test' value= {this.state.service} onChange={this.myChangeHandler}/>
                    </div>
                    <br></br>
                    <br></br>
                    <hr></hr>
                    <br></br>
                    <input type='submit'/>
                </form>
            </div>
        )
    }
}