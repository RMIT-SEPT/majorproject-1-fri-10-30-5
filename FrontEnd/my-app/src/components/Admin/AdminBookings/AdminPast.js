import axios from 'axios'
import BookingRow from "./AdminPastItem";
import React, { useState, useEffect } from "react";

    function AdminPastBooking() {
        
      const [stateBooking, setBookingState] = useState([]);


        useEffect(() => {
            getBooking();
          }, []);
        
    

          const getBooking = () => {
            axios
              .get("http://localhost:8080/api/booking/admin/past-Bookings")
              .then(data => {
                let customer = data.data;
                setBookingState(
                  customer.map(d => {
                    return {
                      id: d.id,
                      custID: d.custID,
                      empID: d.empID,
                      bookingDate: d.bookingDate,
                      bookingStatus: d.bookingStatus,
                      bookingTime: d.bookingTime
                    };
                  })
                );
              })
              .catch((error) => {
                console.log("error",error)
              })    
          };


    
        return (
            <div>
             
              <table className="table" key={stateBooking.id}>
                <thead>
                  <tr>
              
                   <th scope="col">Customer ID</th>
                    <th scope="col">Employee ID</th>
                    <th scope="col">Booking Date</th>
                    <th scope="col">Booking Time</th>
                    <th>
                    
      
                  </th>
                  </tr>
                </thead>
                <tbody>
                  <BookingRow 
                    stateBooking={stateBooking}
                    setBookingState={setBookingState}
                  />
                </tbody> 
              </table>
          
            </div>
          );
        
      }

            export default AdminPastBooking;




