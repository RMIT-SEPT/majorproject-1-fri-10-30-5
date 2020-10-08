import React from 'react'
import Moment from 'moment';

function UpcomingBookingsItem(props) {
        return props.bookingState.map((data, index) => (
                <tr key = {index}>
                    <td id = 'empId'>{data.empID}</td>
                    <td id='date'>{Moment(data.bookingDate).format("DD/MM/YYYY")}</td>
                    <td id='time'>{data.bookingTime}</td>
                    <td id='status'>{data.bookingStatus}</td>
                    <td>
                    <input className = "admin-checkbox"
                    type="checkbox"
                    checked={data.selected}
                    onChange={e => {
                      let value = e.target.checked;
                      props.setBookingState(
                        props.bookingState.map((sd) => {
                          if (sd.id === data.id) {
                            data.selected = value;
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

export default UpcomingBookingsItem;
