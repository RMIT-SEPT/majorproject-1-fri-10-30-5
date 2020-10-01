import React, { Component } from 'react'
import AddEmployeeButton from './AddEmployee/AddEmployeeButton';
import AddServiceButton from './AddService/AddServiceButton';
import AddWorkHoursButton from './AddWorkHours/AddWorkHoursButton';

class AdminPage extends Component {
    render() {
        return (
            <div className="admin-page">
                <h1 className="display-4 text-center">Admin</h1>
                <br />
                <AddEmployeeButton />
                <br />
                <br/>
                <AddServiceButton/>
                <br/>
                <br/>
                <AddWorkHoursButton />
            </div>

        )

    }
}

export default AdminPage;
