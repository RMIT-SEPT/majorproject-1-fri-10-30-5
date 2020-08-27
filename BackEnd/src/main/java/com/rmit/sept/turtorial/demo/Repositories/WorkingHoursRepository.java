package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingHoursRepository extends CrudRepository<WorkingHours, String>
{
    //@Override
    //Iterable<Availability> findAllById(Iterable<int> iterable);

     WorkingHours findAvailabilityByEmpIDEquals(String empID);

     WorkingHours save(WorkingHours workingHours);
}
