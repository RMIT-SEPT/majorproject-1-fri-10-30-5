import React, { Component } from 'react'
import { ScheduleComponent, Day, Week, WorkWeek, Month, Agenda, Inject, ViewsDirective, ViewDirective } from '@syncfusion/ej2-react-schedule';
import "../../../node_modules/@syncfusion/ej2-base/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-buttons/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-calendars/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-dropdowns/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-inputs/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-lists/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-navigations/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-popups/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-splitbuttons/styles/material.css";
import "../../../node_modules/@syncfusion/ej2-react-schedule/styles/material.css";

class WorkerCalendar extends Component {

    constructor() {
        
        super()

        this.state = {
            data: [
                {
                    Id: 1,
                    Subject: 'Wash',
                    StartTime: new Date(2020, 9, 6, 10, 0),
                    EndTime: new Date(2020, 9, 6, 12, 30),
                },
                {
                    Id: 2,
                    Subject: 'Cut',
                    StartTime: new Date(2020, 9, 4, 14, 0),
                    EndTime: new Date(2020, 9, 4, 15, 30),
                }
            ]   
        }
    }

    render() {

        return (
            <div>
                <ScheduleComponent 
                height='550px' 
                selectedDate={new Date(2020, 8, 30)} 
                eventSettings={{ dataSource: this.state.data }} 
                firstDayOfWeek={new Date().getDay()}
                showHeaderBar={false}
                timeScale={{enable: true, interval: 60, slotCount: 1}}
                startHour='08:00'
                endHour='21:00'
                >
                    <ViewsDirective>
                        <ViewDirective option='Week' />
                    </ViewsDirective>
                    <Inject services={[Week]}/>
                </ScheduleComponent>
                <button type='submit'>Book</button>
            </div>
        );
    }
}

export default WorkerCalendar