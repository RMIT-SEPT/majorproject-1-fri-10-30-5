package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.BookingRepository;
import com.rmit.sept.turtorial.demo.Repositories.WorkingHoursRepository;
import com.rmit.sept.turtorial.demo.model.Booking;
import com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
    This class is the service which will be used by the Booking Controller
    to retrieve data stored in the database through the use of the Booking
    Repository. This class also contains the majority of the business logic.
 */
@Service
public class BookingService
{
    //An instance of the Booking Repository
    @Autowired
    private BookingRepository bookingRepository;

    //An instance of the Working Hours Repository
    @Autowired
    private WorkingHoursRepository workingHoursRepository;

    //This methods adds a booking if it doesn't already exist, restrict to 7 days
    public Booking addBooking(Booking booking) throws ParseException
    {
        WorkingHours workingHours = workingHoursRepository.findWorkingHoursByEmpIDEqualsAndWorkDateEqualsAndStartTimeEquals
                (booking.getEmpID(), booking.getBookingDate(), booking.getBookingTime());

        Date currentDate = new Date();
        Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(booking.getBookingDate());

        Calendar current = Calendar.getInstance();
        current.setTime(currentDate);
        Calendar bookingCal = Calendar.getInstance();
        bookingCal.setTime(bookingDate);

        if (bookingCal.get(Calendar.DAY_OF_YEAR)- current.get(Calendar.DAY_OF_YEAR) > 7)
        {
            return null;
        }

        //Checks that the booking is within the working times of the employee
        if(workingHours == null || booking.getBookingTime() < workingHours.getStartTime() ||
                booking.getBookingTime() > workingHours.getEndTime() - 100 || workingHours.getAvailable() == false)
        {
            return null;
        }

          //Checks that the booking doesn't clash with other bookings existing
//        if(!bookings.isEmpty()){
//            for(Booking prevBooking:bookings){
//                if(booking.getBookingDate().equals(prevBooking.getBookingDate())) {
//                    if (!(booking.getBookingTime() <= prevBooking.getBookingTime() - 100
//                            || booking.getBookingTime() >= prevBooking.getBookingTime() + 100))
//                    {
//                        return null;
//                    }
//
//                }
//
//            }
//        }
        workingHours.makeNotAvailable();
        return bookingRepository.save(booking);
    }

    //This method gets all bookings by customer ID
    public List<Booking> findAllBookingsByCustID(String custID)
    {
        if (custID != null)
            return bookingRepository.findAllByCustIDEquals(custID);

        return null;
    }

    //Get a booking by custID and bID
    public Booking findBookingByCustIDAndBID(String custID, Long bID)
    {
        if (custID == null || bID == null)
            return null;

        return bookingRepository.findBookingByCustIDEqualsAndIdEquals(custID, bID);
    }


    //This method updates a pre-existing booking based
    public Booking updateBooking(Booking booking)
    {
        Booking booking1 = bookingRepository.findBookingById(booking.getId());
        WorkingHours workingHours = workingHoursRepository.findWorkingHoursByEmpIDEqualsAndWorkDateEqualsAndStartTimeEquals
                (booking.getEmpID(), booking.getBookingDate(), booking.getBookingTime());

        if (booking1 != null && validateBookingStatus(booking.getBookingStatus()))
        {
            booking1.setBookingStatus(booking.getBookingStatus());
            booking1.setUpdated_At(new Date());
            if (booking1.getBookingStatus() == "cancelled")
                workingHours.makeAvailable();
            return bookingRepository.save(booking1);
        }else{
            return null;
        }
    }

    /*
        This method returns a list of all past/upcoming bookings in
        the database associated with a customer
     */
    public List<Booking> findAllPastOrUpcomingBookingsByCustID(String custID, boolean past)
    {
        //All lists to that will be used
        List<Booking> pastBookings = new ArrayList<Booking>();
        List<Booking> upcomingBookings = new ArrayList<Booking>();
        List<Booking> allBookings = bookingRepository.findAllByCustIDEquals(custID);

        //Formats date and time appropriately
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int dateInt = Integer.parseInt(date);
        String time = new SimpleDateFormat("HHmm").format(new Date());
        int timeInt = Integer.parseInt(time);

        //Sorts bookings based on whether the date is in the past or future
        if(!allBookings.isEmpty()) {
            for (Booking booking : allBookings) {
                int getDateInt = dateToInt(booking.getBookingDate());
                if(getDateInt < dateInt){
                    pastBookings.add(booking);
                }
                else if (getDateInt == dateInt) {
                    if(booking.getBookingTime() <= timeInt) {
                        pastBookings.add(booking);
                    }else{
                        upcomingBookings.add(booking);
                    }
                }else{
                    upcomingBookings.add(booking);
                }
            }
        }

        //Returns the appropriate list based on boolean passed in
        if(past){
            return pastBookings;
        }else{
            return upcomingBookings;
        }
    }

    // Converts date "yyyy-MM-dd" to int yyyyMMdd
    private int dateToInt(String dateString){
        String year = dateString.substring(0,4);
        String month = dateString.substring(5,7);
        String day = dateString.substring(8,10);
        String date = year + month + day;

        return Integer.parseInt(date);
    }

    //Returns a list of all past/upcoming bookings in the database
    public List<Booking> findAllPastOrUpcomingBookings(boolean past)
    {
        //All lists to that will be used
        List<Booking> pastBookings = new ArrayList<Booking>();
        List<Booking> upcomingBookings = new ArrayList<Booking>();
        List<Booking> allBookings = bookingRepository.findAll();

        //Formats date appropriately
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int dateInt = Integer.parseInt(date);
        String time = new SimpleDateFormat("HHmm").format(new Date());
        int timeInt = Integer.parseInt(time);

        //Sorts bookings based on whether the date is in the past or future
        if(!allBookings.isEmpty()) {
            for (Booking booking : allBookings) {
                int getDateInt = dateToInt(booking.getBookingDate());
                if(getDateInt < dateInt){
                    pastBookings.add(booking);
                }
                else if (getDateInt == dateInt) {
                    if(booking.getBookingTime() <= timeInt) {
                        pastBookings.add(booking);
                    }else{
                        upcomingBookings.add(booking);
                    }
                }else{
                    upcomingBookings.add(booking);
                }
            }
        }

        //Returns the appropriate list based on boolean passed in
        if(past){
            return pastBookings;
        }else{
            return upcomingBookings;
        }
    }

    //This method validates the booking status to make sure it has an appropriate value
    private boolean validateBookingStatus(String status)
    {
        String bStatus = status.toLowerCase();
        if (!bStatus.matches("[a-zA-Z ]*$"))
            return false;

        return true;
    }
}
