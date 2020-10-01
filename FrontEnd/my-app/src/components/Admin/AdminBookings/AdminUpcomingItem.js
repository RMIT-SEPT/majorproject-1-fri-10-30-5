import React from "react";
import Moment from 'moment';
import '../../../css/Table.css'

function UpcomingBookingRow(props) {
  return props.stateBooking.map((data,index) => (
    <tr key={index}>
      <td id = 'custID'>{data.custID}</td>
      <td id = 'empID'>{data.empID}</td>
      <td id = 'date'>{Moment(data.bookingDate).format("DD/MM/YYYY")}</td>
      <td id = 'time'>{(data.bookingTime)}</td>
      <td id = 'status'>{data.bookingStatus}</td>
      <td>
      <input className = "admin-checkbox"
        type="checkbox"
        checked={data.select}
        onChange={e => {
          let value = e.target.checked;
          props.setBookingState(
            props.stateBooking.map((sd) => {
              if (sd.id === data.id) {
                data.select = value;
              }
              return sd;
            })
          );
        }}
      />
    </td>
    </tr>
  ));
}

export default UpcomingBookingRow;



