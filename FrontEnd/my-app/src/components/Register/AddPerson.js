import axios from "axios";

const AddPerson = () => {
    const newPerson = {
        firstName: this.state.fname,
        lastName: this.state.lname,
        address: this.state.address,
        phone: this.state.phone,
        userName: this.state.uname,
        password: this.state.pw,
        adminCheck: this.state.uType === "admin" ? true : false,
        employeeCheck: this.state.uType === "employee" ? true : false,
        customerCheck: this.state.uType === "customer" ? true : false
    }
    console.log(newPerson);

    const baseurl = 'http://localhost:8080'
    axios.post(baseurl + '/api/person/add',
        newPerson
    )
        .then(res => //showOutput(res))
        {
            console.log(res);
            console.log(res.data);
        })
        .catch(err => console.error(err));
};

export default AddPerson;