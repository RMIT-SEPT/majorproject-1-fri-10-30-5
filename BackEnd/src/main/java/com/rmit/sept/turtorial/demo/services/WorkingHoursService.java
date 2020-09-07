package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.WorkingHoursRepository;
import com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


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

    //get all Working Hours
    public List<WorkingHours> findAllByEmpIDEquals(String empID) {

        //logic
        return workingHoursRepository.findAllByEmpIDEquals(empID);
    }

    //get Working Hours
    public WorkingHours getWHByEmpID(String empID) {

        //logic
        return workingHoursRepository.findWorkingHoursByEmpIDEquals(empID);
    }

    //get Working Hours by Service and empID
    public List<WorkingHours> getWHByEmpIDandService(String empID, String service) {

        //logic
        return workingHoursRepository.findAllByEmpIDEqualsAndServiceEquals(empID, service);
    }

    //get Working Hours by Service empID and Date
    public List<WorkingHours> getWHByEIDServiceDate(String empID, String service, String date) {

        //logic
        return workingHoursRepository.findAllByEmpIDEqualsAndServiceEqualsAndWorkDateEquals(empID, service, date);
    }
    //get Working Hours by Service empID and Date Time
    public List<WorkingHours> getWHByEIDServiceDateTime(String empID, String service, String date, int start, int end) {

        //logic
        return workingHoursRepository.findAllByEmpIDEqualsAndServiceEqualsAndWorkDateEqualsAndStartTimeIsLessThanEqualAndEndTimeIsLessThanEqual(empID, service, date, start, end);
    }

    //update Working Hours
    public WorkingHours updateWH(WorkingHours workingHours) {
        WorkingHours workingHours1 = workingHoursRepository.findWorkingHoursByEmpIDEquals
                (workingHours.getEmpID());
        if (workingHours1 != null){
            workingHours1.setStartTime(workingHours.getStartTime());
            workingHours1.setEndTime(workingHours.getEndTime());
            workingHours1.setUpdated_At(new Date());
        }
        return workingHoursRepository.save(workingHours1);
    }
}
