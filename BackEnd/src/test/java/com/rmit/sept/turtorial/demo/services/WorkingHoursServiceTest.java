package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.WorkingHoursRepository;
import com.rmit.sept.turtorial.demo.model.WorkingHours;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(WorkingHoursService.class)
public class WorkingHoursServiceTest {

    @MockBean
   private WorkingHoursRepository whr;

    @Autowired
    private  WorkingHoursService whs;

    @Before
    public void setup()
    {

        WorkingHours workHour1 = new WorkingHours(0L, "1",1200,1600,"2020-12-12","Hair Cut");
        WorkingHours workHour2 = new WorkingHours(2L, "2",0700,1200,"2020-08-12","Car Wash");

        List<WorkingHours> workHours = new ArrayList<>();
        workHours.add(workHour1);
        workHours.add(workHour2);
        List<WorkingHours> workHours1 = new ArrayList<>();
        workHours.add(workHour1);

        Mockito.when(whr.findAllByEmpIDEqualsAndServiceEquals(workHour1.getEmpID(),workHour1.getService())).thenReturn(workHours1);
        Mockito.when(whr.findWorkingHoursByEmpIDEquals(workHour1.getEmpID())).thenReturn(workHour1);
        Mockito.when(whr.findWorkingHoursByEmpIDEquals(workHour2.getEmpID())).thenReturn(workHour2);
        Mockito.when(whr.findAll()).thenReturn(workHours);


    }


    @Test
    public void givenValidEmployeeID_EmployeeFoundInWHArray(){
        String empID = "1";

        WorkingHours found = whs.getWHByEmpID(empID);
        assert(found.getEmpID()==empID);}


    @Test
    public void givenValidEmployeeID_WorkingHoursReturn(){
        String empID = "1";

        WorkingHours found = whr.findWorkingHoursByEmpIDEquals(empID);
        assert(found.getEmpID()==empID);
        //assertThat(workHour.getEmpID()).isEqualTo(empID);
    }

//    @Test
//    public void givenValidEmployeeIDandService_WorkingHoursReturn(){
//        String empID = "1";
//        String service = "Hair Cut";
//        List<WorkingHours> workHours = whr.findAllByEmpIDEqualsAndServiceEquals(empID,service);
//        assert();
//    }



}