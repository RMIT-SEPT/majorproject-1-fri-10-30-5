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

    //Adds a booking if it doesn't already exist
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
                booking.getBookingTime() > workingHours.getEndTime() - 100 || !workingHours.getAvailable())
        {
            return null;
        }

        workingHours.makeNotAvailable();
        return bookingRepository.save(booking);
    }

    //Gets all bookings by customer ID
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


    //Updates a pre-existing booking based
    public Booking updateBooking(Booking booking)
    {
        Booking booking1 = bookingRepository.findBookingById(booking.getId());
        WorkingHours workingHours = workingHoursRepository.findWorkingHoursByEmpIDEqualsAndWorkDateEqualsAndStartTimeEquals
                (booking.getEmpID(), booking.getBookingDate(), booking.getBookingTime());

        if (booking1 != null && validateBookingTimeLimit(booking) && validateBookingStatus(booking.getBookingStatus()))
        {
            booking1.setBookingStatus(booking.getBookingStatus());
            booking1.setUpdated_At(new Date());
            if (booking1.getBookingStatus().matches("cancelled"))
                workingHours.makeAvailable();
            return bookingRepository.save(booking1);
        }else{
            return null;
        }
    }

<<<<<<< Updated upstream
    //Gets a list of all past/upcoming bookings in the database associated with a customer
=======


    /*
        This method returns a list of all past/upcoming bookings in
        the database associated with a customer
     */
>>>>>>> Stashed changes
    public List<Booking> findAllPastOrUpcomingBookingsByCustID(String custID, boolean past)
    {
        //All lists to that will be used
        List<Booking> pastBookings = new ArrayList<>();
        List<Booking> upcomingBookings = new ArrayList<>();
        List<Booking> allBookings = bookingRepository.findAllByCustIDEquals(custID);

        //Formats date and time appropriately
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int dateInt = Integer.parseInt(date);
        String time = new SimpleDateFormat("HHmm").format(new Date());
        int timeInt = Integer.parseInt(time);

        //Sorts bookings based on whether the date is in the past or future
        if(!allBookings.isEmpty()) {
            for (Booking booking : allBookings)
            {
                int getDateInt = dateToInt(booking.getBookingDate());
                if(getDateInt < dateInt)
                {
                    pastBookings.add(booking);
                }
                else if (getDateInt == dateInt)
                {
                    if(booking.getBookingTime() <= timeInt)
                    {
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
        if(past)
        {
            return pastBookings;
        }else{
            return upcomingBookings;
        }
    }

    // Converts date "yyyy-MM-dd" to int yyyyMMdd
    private int dateToInt(String dateString)
    {
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
        List<Booking> pastBookings = new ArrayList<>();
        List<Booking> upcomingBookings = new ArrayList<>();
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
        if(past)
        {
            return pastBookings;
        }else{
            return upcomingBookings;
        }
    }

    //Validates the booking status to make sure it has an appropriate value
    private boolean validateBookingStatus(String status)
    {
        String bStatus = status.toLowerCase();
        return bStatus.matches("[a-zA-Z ]*$");
    }
<<<<<<< Updated upstream
}
=======

    private boolean validateBookingTimeLimit(Booking booking) {

        boolean res = true;

        // TODO
        // validate 48hr time limit

        return res;
    }
}
>>>>>>> Stashed changes
