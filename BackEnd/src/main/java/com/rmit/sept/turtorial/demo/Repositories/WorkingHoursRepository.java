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

     //This query finds a working hours object basd on the ID passed in
     WorkingHours findByIdEquals(long id);

     //This query Finds a working hours object based on the employee, date and start time
     WorkingHours findWorkingHoursByEmpIDEqualsAndWorkDateEqualsAndStartTimeEquals(String empID, String bookingDate, int bookingTime);

     //This query finds a list of working hours for an employee after the specified start time on the specified day
     List <WorkingHours> findAllByEmpIDEqualsAndWorkDateEqualsAndStartTimeIsGreaterThanEqual(String empID, String date, int startTime);

     //This query finds all working hours based on the employee ID and shift date
     WorkingHours findAllByEmpIDEqualsAndWorkDateEqualsAndStartTimeIsLessThanEqualAndEndTimeIsLessThanEqual(String empID, String date, int start, int end);

     //This query finds working hours that are available for an employee and shift date
     List<WorkingHours> findAllByEmpIDEqualsAndWorkDateEqualsAndAvailableIsTrue(String empId, String date);

     //This query finds working hours that are not available for an employee and shift date
     List<WorkingHours> findAllByEmpIDEqualsAndWorkDateEqualsAndAvailableIsFalse(String empId, String date);

     //This query determines whether or not an employee has working hours
     Boolean existsByEmpIDEquals(String empID);
}