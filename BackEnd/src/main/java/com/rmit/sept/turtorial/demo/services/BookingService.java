package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.BookingRepository;
import com.rmit.sept.turtorial.demo.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    //post services
    public Booking addBooking(Booking booking){
    //logic
        List<Booking>bookings=bookingRepository.findAllByCustIDEquals(booking.getCustID());
        if(!bookings.isEmpty()){
            for(Booking prevBooking:bookings){
                if(booking.getBookingDate().equals(prevBooking.getBookingDate())){
                    if(!(booking.getBookingTime()<=prevBooking.getBookingTime()-60||booking.getBookingTime()>=prevBooking.getBookingTime()+60))
                        return null;
                }
            }
        }
        return bookingRepository.save(booking);
    }


    //get services
    public Booking getBookingByCustomerUserName(String userName) {

        //logic
        return bookingRepository.findBookingByCustIDEquals(userName);
    }

    //get all bookings by custID
    public List<Booking> findAllBookingsByCustID(String custID) {

        //logic
        return bookingRepository.findAllByCustIDEquals(custID);
    }

    //get a booking by custID and bID
    public Booking findBookingByCustIDAndBID(String custID, Long bID) {

        //logic
        return bookingRepository.findBookingByCustIDEqualsAndIdEquals(custID, bID);
    }


    //put services
    public Booking updateBooking(Booking booking) {
        Booking booking1 = bookingRepository.findBookingByCustIDEquals(booking.getCustID());
        if (booking1 != null){
            booking1.setBookingStatus(booking.getBookingStatus());
            booking1.setUpdated_At(new Date());
            return bookingRepository.save(booking1);
        }else{
            return null;
        }
    }

    //get all past/upcoming bookings by custID
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

    // Convert date "yyyy-MM-dd" to int yyyyMMdd
    private int dateToInt(String dateString){
        String year = dateString.substring(0,4);
        String month = dateString.substring(5,7);
        String day = dateString.substring(8,10);
        String date = year + month + day;

        return Integer.parseInt(date);
    }

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
