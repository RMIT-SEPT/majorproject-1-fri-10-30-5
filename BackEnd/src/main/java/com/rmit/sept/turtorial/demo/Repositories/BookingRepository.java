package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
    This interface contains all queries to be executed
    on the Booking table in the database
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long>
{
    //This query finds a collection of all Bookings based on ID
    @Override
    Iterable<Booking> findAllById(Iterable<Long> iterable);

    //This query finds a booking based on the customer ID and booking ID passed in
    Booking findBookingByCustIDEqualsAndIdEquals(String custID, Long bID);

    //This query finds a booking by the booking ID passed in
    Booking findBookingById(Long bID);

    //This query finds a list of all queries the customer ID passed in
    List<Booking> findAllByCustIDEquals(String custID);

    //This query finds all Bookings in the database
    List<Booking> findAll();

    //This query determines if a booking exists based on its booking ID
    Boolean existsByIdEquals(long id);
}