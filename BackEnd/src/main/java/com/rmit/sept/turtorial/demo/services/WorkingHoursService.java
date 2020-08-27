package main.java.com.rmit.sept.turtorial.demo.services;

import main.java.com.rmit.sept.turtorial.demo.Repositories.WorkingHoursRepository;
import main.java.com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorkingHoursService
{
    @Autowired
    private WorkingHoursRepository workingHoursRepository;

    //add Working Hours
    public WorkingHours addWH(WorkingHours workingHours) {

        //logic
        return workingHoursRepository.save(workingHours);
    }

    //get Working Hours
    public WorkingHours getWHByEmpID(String empID) {

        //logic
        return workingHoursRepository.findAvailabilityByEmpIDEquals(empID);
    }

    //update Working Hours
    public WorkingHours updateWH(WorkingHours workingHours) {
        WorkingHours workingHours1 = workingHoursRepository.findAvailabilityByEmpIDEquals
                (workingHours.getEmpID());
        if (workingHours1 != null){
            workingHours1.setStartTime(workingHours.getStartTime());
            workingHours1.setEndTime(workingHours.getEndTime());
            workingHours1.setUpdated_At(new Date());
        }
        return workingHoursRepository.save(workingHours1);
    }
}
