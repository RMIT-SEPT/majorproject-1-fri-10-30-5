import React from "react";
import Moment from 'moment';

function PastBookingRow(props) {
  return props.stateBooking.map((data,index) => (
    <tr key={index}>
      <td id = 'custID'>{data.custID}</td>
      <td id = 'empID'>{data.empID}</td>
      <td id = 'date'>{Moment(data.bookingDate).format("DD/MM/YYYY")}</td>
      <td id = 'time'>{(data.bookingTime)}</td>
      <td>
    </td>
      <td>

      </td>
    </tr>
  ));
}

export default PastBookingRow;



