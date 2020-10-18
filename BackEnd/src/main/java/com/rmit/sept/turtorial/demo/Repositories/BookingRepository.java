package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
    This interface contains all queries to be executed
    on the Booking database
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long>
{
    //Returns an iterable collection of all Bookings based on ID
    @Override
    Iterable<Booking> findAllById(Iterable<Long> iterable);

    //Finds a booking for a customer through an ID
    Booking findBookingByCustIDEqualsAndIdEquals(String custID, Long bID);

    //Finds a booking through an ID
    Booking findBookingById(Long bID);

    //Finds all bookings for a customer
    List<Booking> findAllByCustIDEquals(String custID);

    //Finds all bookings in the database
    List<Booking> findAll();
}