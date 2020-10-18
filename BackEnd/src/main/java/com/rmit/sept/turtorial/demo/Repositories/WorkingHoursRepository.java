package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
     This interface contains all queries that will be executed
     on the Working Hours database
 */
@Repository
public interface WorkingHoursRepository extends CrudRepository<WorkingHours, Long>
{
     //Finds an iterable collection of working hours based on the supplied ID
     @Override
     Iterable<WorkingHours> findAllById(Iterable<Long> iterable);

     //Finds all working hours for an employee
     List<WorkingHours> findAllByEmpIDEquals(String empID);

     //Finds a working hour based on the ID number
     WorkingHours findByIdEquals(long id);

     //Finds a working hour for an employee on a certain date and start time
     WorkingHours findWorkingHoursByEmpIDEqualsAndWorkDateEqualsAndStartTimeEquals(String empID, String bookingDate, int bookingTime);

     //Finds all working hours for an employee after the specified start time, on the specified day
     List <WorkingHours> findAllByEmpIDEqualsAndWorkDateEqualsAndStartTimeIsGreaterThanEqual(String empID, String date, int startTime);

     //Finds a working hour based on the employee, shift date, and start and end time
     WorkingHours findAllByEmpIDEqualsAndWorkDateEqualsAndStartTimeIsLessThanEqualAndEndTimeIsLessThanEqual(String empID, String date, int start, int end);

     //Finds all working hours that are available for an employee and shift date
     List<WorkingHours> findAllByEmpIDEqualsAndWorkDateEqualsAndAvailableIsTrue(String empId, String date);

     //Finds all working hours that are not available for an employee and shift date
     List<WorkingHours> findAllByEmpIDEqualsAndWorkDateEqualsAndAvailableIsFalse(String empId, String date);

     //Determines whether or not an employee has working hours
     Boolean existsByEmpIDEquals(String empID);
}