import UpcomingBookingsItem from '../../components/Bookings/UpcomingBookingsItem'
import axios from "axios";
import React, { useState, useEffect } from "react";
import "../../css/Table.css"


function UpcomingBookings(props) {
    const [bookingState, setBookingState] = useState([]);

    useEffect(() => {
        getBooking();
    }, []);



    const getBooking = () => {
        const uname = props.user.username;
        const url = 'http://localhost:8080/api/booking/upcomingBookings/list/' + uname;
        axios.get(url)
            .then(res => {
                let booking = res.data
                setBookingState(
                    booking.map(d => {
                        return {
                            selected: false,
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
                console.log("error", error)
            })
    };

    const cancelBooking = () => {
        bookingState.forEach(data => {
            if (data.selected) {
                const booking = {
                    id: data.id,
                    custID: data.custID,
                    empID: data.empID,
                    bookingDate: data.bookingDate,
                    bookingStatus: "cancelled",
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
                        console.log("error", error)
                    })
            }
        });
        window.location.reload(false);

    };

    const results = () => {
        if (bookingState !== null) {
            return <div>
                <table className="table table-striped">
                    <thead className="thead">
                        <tr>
                            <th scope="col">Worker</th>
                            <th scope="col">Date</th>
                            <th scope="col">Time</th>
                            <th scope="col">Status</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <UpcomingBookingsItem
                            bookingState={bookingState}
                            setBookingState={setBookingState}
                        />
                    </tbody>
                </table>
                <button className="btn btn-danger btn-lg m-2"
                    onClick={() => {
                        cancelBooking();
                    }}>
                    Cancel Booking </button>
            </div>
        } else {
            return <h5>You have no upcoming bookings. Make a booking <a href="/search">here</a>.</h5>
        }
    }

    return (
        <div>
            <h5 className="display-4 text-center">Upcoming Bookings</h5>
            <br />
            {results()}
        </div>
    );
}

export default UpcomingBookings;