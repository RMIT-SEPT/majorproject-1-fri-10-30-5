package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.BookingRepository;
import com.rmit.sept.turtorial.demo.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    //post services
    public Booking addBooking(Booking booking) {

        //logic
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
}
