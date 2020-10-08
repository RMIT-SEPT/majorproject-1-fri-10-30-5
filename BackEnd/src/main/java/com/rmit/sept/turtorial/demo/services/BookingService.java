package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.BookingRepository;
import com.rmit.sept.turtorial.demo.Repositories.WorkingHoursRepository;
import com.rmit.sept.turtorial.demo.model.Booking;
import com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private WorkingHoursRepository workingHoursRepository;

    //Adds a booking
    public Booking addBooking(Booking booking)
    {
        List<Booking>bookings=bookingRepository.findAllByCustIDEquals(booking.getCustID());
        WorkingHours workingHours = workingHoursRepository.findWorkingHoursByEmpIDEqualsAndWorkDateEquals(booking.getEmpID(), booking.getBookingDate());


        if(!bookings.isEmpty()){
            for(Booking prevBooking:bookings){
                if(booking.getBookingDate().equals(prevBooking.getBookingDate())) {
                    if (!(booking.getBookingTime() <= prevBooking.getBookingTime() - 100
                            || booking.getBookingTime() >= prevBooking.getBookingTime() + 100))
                    {
                        return null;
                    }

                }

            }
        }

        if(workingHours == null || booking.getBookingTime() < workingHours.getStartTime() ||
                booking.getBookingTime() > workingHours.getEndTime() - 100)
        {
            return null;
        }

        return bookingRepository.save(booking);

    }


    //Gets a booking based on customer userName
    public Booking getBookingByCustomerUserName(String userName) {

        //logic
        return bookingRepository.findBookingByCustIDEquals(userName);
    }

    //Get all bookings by custID
    public List<Booking> findAllBookingsByCustID(String custID) {

        if (custID == null)
            return null;

        return bookingRepository.findAllByCustIDEquals(custID);
    }

    //Get a booking by custID and bID
    public Booking findBookingByCustIDAndBID(String custID, Long bID) {

        if (custID == null || bID == null)
            return null;

        return bookingRepository.findBookingByCustIDEqualsAndIdEquals(custID, bID);
    }


    //Updates pre-existing booking
    public Booking updateBooking(Booking booking) {

        Booking booking1 = bookingRepository.findBookingById(booking.getId());
        WorkingHours workingHours1 = workingHoursRepository.findWorkingHoursByEmpIDEqualsAndWorkDateEquals(booking1.getEmpID(), booking1.getBookingDate());

        if (booking1 != null && workingHours1 != null && !(booking.getBookingTime() < workingHours1.getStartTime())
                && !(booking.getBookingTime() > workingHours1.getEndTime() - 100))
        {
            booking1.setBookingDate(booking.getBookingDate());
            booking1.setBookingTime(booking.getBookingTime());
            booking1.setCustID(booking.getCustID());
            booking1.setEmpID(booking.getEmpID());
            booking1.setBookingStatus(booking.getBookingStatus());
            booking1.setUpdated_At(new Date());
            return bookingRepository.save(booking1);
        }else{
            return null;
        }
    }

    //Returns a list of all past/upcoming bookings in the database associated with a customer
    public List<Booking> findAllPastOrUpcomingBookingsByCustID(String custID, boolean past) {
        List<Booking> pastBookings = new ArrayList<Booking>();
        List<Booking> upcomingBookings = new ArrayList<Booking>();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int dateInt = Integer.parseInt(date);
        String time = new SimpleDateFormat("HHmm").format(new Date());
        int timeInt = Integer.parseInt(time);
        List<Booking> allBookings = bookingRepository.findAllByCustIDEquals(custID);
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
        List<Booking> pastBookings = new ArrayList<Booking>();
        List<Booking> upcomingBookings = new ArrayList<Booking>();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int dateInt = Integer.parseInt(date);
        String time = new SimpleDateFormat("HHmm").format(new Date());
        int timeInt = Integer.parseInt(time);
        List<Booking> allBookings = bookingRepository.findAll();
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
        if(past){
            return pastBookings;
        }else{
            return upcomingBookings;
        }
    }
}
