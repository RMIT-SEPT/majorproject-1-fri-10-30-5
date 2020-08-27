package main.java.com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.Booking;
import main.java.com.rmit.sept.turtorial.demo.model.WorkingHours;

public interface WorkingHoursRepository
{
    //@Override
    //Iterable<Availability> findAllById(Iterable<int> iterable);

     WorkingHours findAvailabilityByEmpIDEquals(String empID);
}
