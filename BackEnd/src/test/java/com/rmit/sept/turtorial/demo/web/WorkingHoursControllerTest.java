package com.rmit.sept.turtorial.demo.web;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.rmit.sept.turtorial.demo.model.Booking;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.WorkingHours;
import com.rmit.sept.turtorial.demo.services.WorkingHoursService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(WorkingHoursController.class)
public class WorkingHoursControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMap;

    @MockBean
    private  WorkingHoursService whs;


    @Test
    public void givenValidWorkingHours_whenPostWorkingHours_thenSuccessfullyCreated() throws Exception {
        WorkingHours workHour = new WorkingHours(0L, "1",1200,1600,"2020-12-12");

        when(whs.addWH(any(WorkingHours.class))).thenReturn(workHour);
        mvc.perform(post("/api/workinghours/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.empID",is("1")))
                .andExpect(jsonPath("$.startTime",is(1200)))
                .andExpect(jsonPath("$.workDate",is("2020-12-12")));
    }

    @Test
    public void givenNullWorkingHours_whenPostWorkingHours_thenBadRequest() throws Exception {
        WorkingHours workHour = new WorkingHours(null, null,1200,1600,"2020-12-12");
        String nullWork = "Invalid Working Hours Object";

        mvc.perform(post("/api/workinghours/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(jsonPath("$",is(nullWork)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenEndTimeOutOfBounds_whenPostWorkingHours_thenBadRequest() throws Exception {
        WorkingHours workHour = new WorkingHours(0L, "1",1200,2500,"2020-12-12");
        String nullWork = "Invalid Working Hours Object";

        mvc.perform(post("/api/workinghours/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(jsonPath("$",is(nullWork)))
                .andExpect(status().isBadRequest());
    }



    @Test
    public void givenInvalidWorkingHours_whenUpdateWorkingHour_thenBadRequest() throws Exception {
        WorkingHours workHour = new WorkingHours(0L, null,1200,1600,"2020-12-12");
        String message = "Invalid Working Hours Object";

        mvc.perform(put("/api/workinghours/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(workHour)))
                .andExpect(jsonPath("$",is(message)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenEmployee_whenGetWorkingHours_thenReturnArray() throws Exception {
        WorkingHours workHour = new WorkingHours(0L, "1",1200,1600,"2020-12-12");
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


    @Test
    public void givenEmployeeNotExists_whenGetWorkingHours_thenNotFound() throws Exception {
        mvc.perform(get("/api/workingHours/list/{empID}","emp30")
                .contentType(MediaType.APPLICATION_JSON))
             //   .andExpect(jsonPath("$", is("No Working Hours Objects")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenInvalidEmployeesAndService_whenGetWorkingHours_thenNotFound() throws Exception {
        String message = "No Working Hours Objects";
        mvc.perform(get("/api/workinghours/list/{empID}","30","Hair Cut")
                .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(jsonPath("$", is(message)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenEmployeesAndServiceAndDate_whenGetWorkingHours_thenReturnArray() throws Exception {
        WorkingHours workHour = new WorkingHours(0L, "1",1200,
                1600,"2020-12-12");
        List<WorkingHours> allWorkingHours = Arrays.asList(workHour);

//        given(whs.getWHByEIDServiceDateTime("1","Hair Cut","2020-12-12",1200,1600)).willReturn(allWorkingHours);
//
//        mvc.perform(get("/api/workinghours/list/{empID}/{service}/{date}/{startTime}/{endTime}","1","Hair Cut","2020-12-12",1200,1600)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$",hasSize(1)))
//                .andExpect(jsonPath("$[0].empID",is(workHour.getEmpID())))
//                .andExpect(jsonPath("$[0].startTime",is(workHour.getStartTime())))
//                .andExpect(jsonPath("$[0].workDate",is(workHour.getWorkDate())))
//                .andExpect(jsonPath("$[0].service",is(workHour.getService())))
//                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidEmployeesAndServiceAndDate_whenGetWorkingHours_thenNotFound() throws Exception {
        //String message = "No Working Hours Objects";
        mvc.perform(get("/api/workinghours/list/{empID}/{date}/{startTime}/{endTime}","1","Hair Cut","2020-12-12",1200,1600)
                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", is(message)))
                .andExpect(status().isBadRequest());
    }
}


//    @Test
//    public void givenWorkingHours_whenUpdateEmployees_thenBadRequest() throws Exception {
//        WorkingHours workHour = new WorkingHours(0L, "1",1200,1600,"2020-12-12","Hair Cut");
//
//        when(whs.updateWH(workHour)).thenReturn(workHour);
//        mvc.perform(put("/api/workinghours/update")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.empID",is(workHour.getEmpID())))
//                .andExpect(jsonPath("$.startTime",is(workHour.getStartTime())))
//                .andExpect(jsonPath("$.workDate",is(workHour.getWorkDate())))
//                .andExpect(jsonPath("$.service",is(workHour.getService())))
//                .andExpect(status().isOk());
//    }