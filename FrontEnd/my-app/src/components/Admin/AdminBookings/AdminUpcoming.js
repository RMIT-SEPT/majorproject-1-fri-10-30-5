import axios from 'axios'
import BookingRow from "./AdminUpcomingItem";
//import '../src/css/AdminBookings.css';
import React, { useState, useEffect } from "react";

    function AdminUpcomingBooking() {
        
      const [stateBooking, setBookingState] = useState([]);


        useEffect(() => {
            getBooking();
          }, []);
        
    

          const getBooking = () => {
            axios
              .get("http://localhost:8080/api/booking/admin/upcoming-Bookings")
              .then(data => {
                let customer = data.data;
                setBookingState(
                  customer.map(d => {
                    return {
                      select: false,
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

          const cancelBooking = () => {
            stateBooking.forEach(data => {
              if (data.select) {
                const booking ={
                  id: data.id,
                  custID: data.custID,
                  empID: data.empID,
                  bookingDate: data.bookingDate,
                  bookingStatus: "cancel",
                  bookingTime: data.bookingTime
                
                }
                 axios
                 .put(`http://localhost:8080/api/booking/update`,
                 booking)
                 .then(data => {
                   console.log(booking);
                //   getBooking();
                 })
                 .catch((error) => {
                   console.log("error",error)
                 })    
              }
            });
            window.location.reload(false);
            
          }


    
        return (
            <div>
             
              <table className="table" key={stateBooking.id}>
                <thead>
                  <tr>
              
                   <th scope="col">Customer ID</th>
                    <th scope="col">Employee ID</th>
                    <th scope="col">Booking Date</th>
                    <th scope="col">Booking Time</th>
                    <th scope="col">Status</th>
                    <th>
                    
                    <input
                      type="checkbox"
                      onChange={e => {
                        let value = e.target.checked;
                        setBookingState(
                          stateBooking.map((d) => {
                            d.select = value; 
                            return d;
                          })
                        );
                      }}
                    />
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
              <button
             className="btn btn-primary btn-sm m-2" style = {{backgroundColor: "#341930",border: "1px solid #341930"}}
              onClick={() => {
                  cancelBooking();
              }}
            >
              Cancel Booking
            </button>
            </div>
          );
        
      }

            export default AdminUpcomingBooking;




