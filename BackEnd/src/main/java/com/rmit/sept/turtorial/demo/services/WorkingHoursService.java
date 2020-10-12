package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.WorkingHoursRepository;
import com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    This class is the service which will be used by the Working Hours Controller
    to retrieve data stored in the database through the use of the Working Hours
    Repository. This class also contains the majority of the business logic.
 */
@Service
public class WorkingHoursService
{
    //An instance of the Working Hours Repository
    @Autowired
    private WorkingHoursRepository workingHoursRepository;

    //This method adds a Working Hours object if it doesn't already exist
    public List<WorkingHours> addWH(WorkingHours workingHours)
    {
        if (workingHours.getStartTime() >= workingHours.getEndTime())
            return null;
        else {

            //Start and end times of the working hours passed in
            int start = workingHours.getStartTime();
            int end = workingHours.getEndTime();

            int max = (end - start)/ 100;

            //List to store each working hours object
            List<WorkingHours> workingHoursList = new ArrayList<>();

            /*
                On each iteration of the loop create a working hours object for
                each hour of the shift
             */
            for (int i = 0; i < max; ++i)
            {
                WorkingHours workingHours1 = new WorkingHours();
                workingHours1.setEmpID(workingHours.getEmpID());
                workingHours1.setWorkDate(workingHours.getWorkDate());
                workingHours1.setStartTime(start + 100 * i);
                workingHours1.setEndTime(workingHours1.getStartTime() + 100);
                workingHoursList.add(workingHours1);
                workingHoursRepository.save(workingHours1);
            }

            return workingHoursList;
        }
    }

    //This method returns all Working Hours an employee has
    public List<WorkingHours> findAllByEmpIDEquals(String empID)
    {
        if (!workingHoursRepository.existsByEmpIDEquals(empID))
            return null;

        return workingHoursRepository.findAllByEmpIDEquals(empID);
    }

    //This method gets a list of Working Hours after a specified start time
    public List<WorkingHours> getWHByEIDServiceDate(String empID, String date, int startTime) {

        return workingHoursRepository.findAllByEmpIDEqualsAndWorkDateEqualsAndStartTimeIsGreaterThanEqual(empID, date, startTime);
    }

    //This method gets a Working Hours by the specified start and end time
    public WorkingHours getWHByEIDDateTime(String empID, String date, int start, int end)
    {
        if (empID == null || date == null)
            return null;

        return workingHoursRepository.findAllByEmpIDEqualsAndWorkDateEqualsAndStartTimeIsLessThanEqualAndEndTimeIsLessThanEqual(empID, date, start, end);
    }

    //Gets a list of working hours that an employee is available for on a certain day
    public List<WorkingHours> availableWHByEIDDate(String empID, String date)
    {
        return workingHoursRepository.findAllByEmpIDEqualsAndWorkDateEqualsAndAvailableIsTrue
                    (empID, date);
    }

    //Gets a list of working hours that an employee is unavailable for on a certain day
    public List<WorkingHours> unavailableWHByEIDDate(String empID, String date)
    {
        return workingHoursRepository.findAllByEmpIDEqualsAndWorkDateEqualsAndAvailableIsFalse
                (empID, date);
    }

    //This method updates an existing Working Hours object
    public WorkingHours updateWH(WorkingHours workingHours)
    {
        WorkingHours workingHours1 = workingHoursRepository.findByIdEquals(workingHours.getId());
        if (workingHours1 != null)
        {
            workingHours1.setStartTime(workingHours.getStartTime());
            workingHours1.setEndTime(workingHours.getEndTime());
            workingHours1.setUpdated_At(new Date());
        }
        return workingHoursRepository.save(workingHours1);
    }
}
