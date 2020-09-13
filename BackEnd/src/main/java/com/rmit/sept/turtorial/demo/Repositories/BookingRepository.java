package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Override
    Iterable<Booking> findAllById(Iterable<Long> iterable);

    Booking findBookingByCustIDEquals(String custID);

    Booking findBookingByCustIDEqualsAndIdEquals(String custID, Long bID);

    List<Booking> findAllByCustIDEquals(String custID);
}
