package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
     This interface contains all queries that will be executed
     on the Working Hours table in the database
 */
@Repository
public interface WorkingHoursRepository extends CrudRepository<WorkingHours, Long>
{
     //This query finds a collection of working hours based on the supplied ID
     @Override
     Iterable<WorkingHours> findAllById(Iterable<Long> iterable);

     //This query find all working hours based on the employee ID passed in
     List<WorkingHours> findAllByEmpIDEquals(String empID);

     //Finds a working hours object basd on the ID passed in
     WorkingHours findByIdEquals(long id);

     //This query finds all working hours based on the employee ID and work date passed in
     WorkingHours findWorkingHoursByEmpIDEqualsAndWorkDateEquals(String empID, String date);

     //This query finds all working hours based on the employee ID and shift date
     List<WorkingHours> findAllByEmpIDEqualsAndWorkDateEqualsAndStartTimeIsLessThanEqualAndEndTimeIsLessThanEqual(String empID, String date, int start, int end);

     //This query determines whether or not an employee has working hours
     Boolean existsByEmpIDEquals(String empID);

     //This query determines whether or not an employee has working hours for a specified day
     Boolean existsByEmpIDEqualsAndWorkDateEquals(String empID, String date);

     //This query determines whether a booking exists or not by ID
     Boolean existsByIdEquals(long id);
}