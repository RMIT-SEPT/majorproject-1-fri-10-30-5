package com.rmit.sept.turtorial.demo.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.WorkingHours;
import com.rmit.sept.turtorial.demo.services.WorkingHoursService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    This class contains tests for each method in the Working Hours Controller class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkingHoursControllerTest
{
    //An instance of the MockMvc Object
    @Autowired
    private MockMvc mvc;

    //An instance of the Object Mapper Object
    @Autowired
    private ObjectMapper objectMap;

    //An instance of the Working Hours Service
    @MockBean
    private WorkingHoursService whs;

    //This method tests that valid Working Hours can be posted correctly
    @Test
    public void givenValidWorkingHours_whenPostWorkingHours_thenCreated() throws Exception
    {
        WorkingHours workHour = new WorkingHours(0L, "1",1200,
                1600,"2020-12-12");
        List <WorkingHours> workingHours = new ArrayList<>();
        workingHours.add(new WorkingHours(1L, "1", 1200,
                1300, "2020-12-12"));
        workingHours.add(new WorkingHours(2L, "1", 1300,
                1400, "2020-12-12"));
        workingHours.add(new WorkingHours(3L, "1", 1400,
                1500, "2020-12-12"));
        workingHours.add(new WorkingHours(4L, "1", 1500,
                1600, "2020-12-12"));

        when(whs.addWH(any(WorkingHours.class))).thenReturn(workingHours);
        mvc.perform(post("/api/workinghours/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(status().isCreated());
    }

    //This method tests that posting a null working hours object returns a bad request
    @Test
    public void givenNullWorkingHours_whenPostWorkingHours_thenBadRequest() throws Exception
    {
        WorkingHours workHour = new WorkingHours(null, null,1200,
                1600,"2020-12-12");
        String nullWork = "Invalid Working Hours Object";

        mvc.perform(post("/api/workinghours/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(jsonPath("$",is(nullWork)))
                .andExpect(status().isBadRequest());
    }

    //This method tests that posting an invalid end time returns a bad request
    @Test
    public void givenEndTimeOutOfBounds_whenPostWorkingHours_thenBadRequest() throws Exception
    {
        WorkingHours workHour = new WorkingHours(0L, "1",1200,
                2500,"2020-12-12");
        String nullWork = "Invalid Working Hours Object";

        mvc.perform(post("/api/workinghours/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(jsonPath("$",is(nullWork)))
                .andExpect(status().isBadRequest());
    }

    //This method tests that updating a working hours object with invalid values returns a bad request
    @Test
    public void givenInvalidWorkingHours_whenUpdateWorkingHour_thenBadRequest() throws Exception
    {
        WorkingHours workHour = new WorkingHours(0L, null,1200,
                1600,"2020-12-12");
        String message = "Invalid Working Hours Object";

        mvc.perform(put("/api/workinghours/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(jsonPath("$",is(message)))
                .andExpect(status().isBadRequest());
    }

    //This method tests that all working hours for an employee can be retrieved
    @Test
    public void givenEmployee_whenGetWorkingHours_thenOk() throws Exception
    {
        WorkingHours workHour = new WorkingHours(0L, "1",1200,
                1600,"2020-12-12");
        List<WorkingHours> allWorkingHours = Arrays.asList(workHour);

        given(whs.findAllByEmpIDEquals("1")).willReturn(allWorkingHours);

        mvc.perform(get("/api/workinghours/list/{empID}","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].empID",is(workHour.getEmpID())))
                .andExpect(jsonPath("$[0].startTime",is(workHour.getStartTime())))
                .andExpect(jsonPath("$[0].workDate",is(workHour.getWorkDate())))
                .andExpect(status().isOk());
    }

    //This method tests that a bad request is returned when getting for a non-existent employee
    @Test
    public void givenEmployeeNotExists_whenGetWorkingHours_thenNotFound() throws Exception
    {
        mvc.perform(get("/api/workingHours/list/{empID}","emp30")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    //This method tests that the correct error message is displayed
    @Test
    public void givenInvalidEmployees_whenGetWorkingHours_thenNotFound() throws Exception
    {
        String message = "No Working Hours Objects";
        mvc.perform(get("/api/workinghours/list/{empID}","30","Hair Cut")
                .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(jsonPath("$", is(message)))
                .andExpect(status().isNotFound());
    }

    //This method tests that a bad request is returned when inputting invalid employees and dates
    @Test
    public void givenInvalidEmployeesAndDate_whenGetWorkingHours_thenBadRequest() throws Exception
    {
        mvc.perform(get("/api/workinghours/list/{empID}/{date}/{startTime}/{endTime}",
                "1","Hair Cut","2020-12-12",1200,1600)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}