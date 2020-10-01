import axios from 'axios'
import React, { Component } from 'react'

class AssignService extends Component {

  state ={
    services:[],
    employees:[],
    serviceId:'',
    userName:''
  }

  componentDidMount(){
    axios.get('http://localhost:8080/api/person/employee/list/')
      .then(res => {
        this.setState({
          employees: res.data,
          userName: res.data[0].userName
        })
      })
      .catch((error) => {
        console.log("error",error)
      })  
      
      axios.get('http://localhost:8080/api/service/list/')
        .then(res => {
          this.setState({
            services: res.data,
            serviceId: res.data[0].serviceId
          })
        })
        .catch((error) => {
          console.log("error",error)
        }) 
}

mySubmitHandler = event => {
  const assignService = {
      serviceId: this.state.serviceId,
      userName: this.state.userName
  }

  axios.post('http://localhost:8080/api/assignService/add', 
  assignService
    )
    .then(res => 
    {console.log(res.data);
   })
    .catch(err => console.error(err));
}

myChangeHandler = event => {
  this.setState({[event.target.name]: event.target.value})
};

render(){
  return (
    <div className="drop-down">
        <p>Employee User</p>
            <select name = "userName"  onChange = {this.myChangeHandler}>{
              this.state.employees.map((employee,index) => <option key={index} value={employee.userName} >{employee.userName}  </option>)}
            </select>
            <br></br>
        <p>Service Name</p>
              <select  name = "serviceId"   onChange = {this.myChangeHandler}>{
                this.state.services.map((service,index) => <option key={index} value= {service.serviceId} >{service.name}</option>) }
              </select>
        <br></br>
        <br></br>
        <button className="btn btn-primary btn-sm m-2"
          onClick={() => {
            this.mySubmitHandler();
        }}
      >
        Assign Service
      </button>
        </div>
  )
}

}

export default AssignService;

   






























// function AssignService() {
        
    //   const [stateEmployees, setEmployeesState] = useState([]);
    //   const [stateServices, setServicesState] = useState([]);
     

    //     useEffect(() => {
    //         getEmployee();
    //       }, []);

    //       useEffect(() => {
    //         getService();
    //       }, []);
        
    

    //       const getEmployee = () => {
    //         axios
    //           .get("http://localhost:8080/api/person/employee/list/")
    //           .then(data => {
    //             let employee = data.data;
    //             setEmployeesState(
    //               employee.map(d => {
    //                 return {
            
    //                   // id: d.id,
    //                   userName: d.userName,
    //                   checked: false
    //                   // password: d.password,
    //                   // firstName: d.firstName,
    //                   // lastName: d.lastName,
    //                   // address: d.address,
    //                   // phone: d.phone

               
                     
    //                 };
    //               })
    //             );
    //           })
    //           .catch((error) => {
    //             console.log("error",error)
    //           })    
    //       };

    //       const getService = () => {
    //         axios
    //           .get("http://localhost:8080/api/service/list/")
    //           .then(data => {
    //             let service = data.data;
    //             setServicesState(
    //               service.map(d => {
    //                 return {
    //                   // id: d.iD,
    //                   name: d.name,
    //                   // description: d.description,
    //                   // duration: d.duration
    //                 };
    //               })
    //             );
    //           })
    //           .catch((error) => {
    //             console.log("error",error)
    //           })    
    //       };
          

    //       const addAssignService  = e => {
    //         console.log(stateEmployees);
    //         console.log(stateServices);
    //         stateEmployees.forEach(data => {
    //           if(data.checked = true){
    //             const serviceName = data.userName
             

    //           const assignService = {
    //             serviceName: "cut",
    //             userName: serviceName

    //           }
    //           console.log(assignService);
    //           axios
    //             .post(`http://localhost:8080/api/assignService/add`, assignService)
    //             .then(data => {
    //               console.log(assignService);
    //             })
    //             .catch((error) => {
    //               console.log("error",error)
    //             })    
    //         }
    //       }
    //       );}


    //       return (
    //         <div className="container my-3">
    //         <table className="table" key={stateEmployees} >
             
    //             <thead>
    //             </thead>
    //             <tbody>
    //             <th scope="col">Employee User
    //             <select>
           
    //               {stateEmployees.map(d =>(
    //                 <option key={d.id}
    //                  value={d.userName}
    //                 onClick={e => {
    //                   // let value = e.target.value;
    //                   setEmployeesState({
    //                     // id: d.iD,
    //                     userName: d.userName,
    //                     checked: true
    //                     // description: d.description,
    //                     // duration: d.duration
    //                   });
                    
    //                 }}>
    //                 {d.userName}
    //                 </option>
                
               
                  
    //               ))};
                
    //               </select>
    //               </th>
    //               <th scope="col">Service Name
    //               <select>
    //                 {stateServices.map(d =>(
    //                   <option key={d.iD} 
    //                   value={d.name}  
    //                   onClick={e => {
    //                     // let value = e.target.name;
    //                     setServicesState({
    //                       // id: d.id,
    //                       serviceName: d.name,
    //                       checked: true
    //                       // password: d.password,
    //                       // firstName: d.firstName,
    //                       // lastName: d.lastName,
    //                       // address: d.address,
    //                       // phone: d.phone
    //                     });
                      
    //                   }}>
    //                   {d.name}
    //                   </option>
    //                 ))};
    //                 </select>
    //                 </th>
    //               </tbody> 
                 
    //               <button
    //           className="Button"
    //           onClick={() => {
    //               addAssignService();
    //           }}
    //         >
    //           Cancel Booking
    //         </button>
                

    //           </table>
            
         
    //         </div>
    //       );
        
    //   }

    // export default AssignService;
