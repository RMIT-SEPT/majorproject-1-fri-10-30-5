package com.rmit.sept.turtorial.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.AssignedService;
import com.rmit.sept.turtorial.demo.model.Person;
import com.rmit.sept.turtorial.demo.services.AssignedServiceService;
import com.rmit.sept.turtorial.demo.services.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//This class contains tests for the Assigned Service Controller class
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AssignedServiceControllerTest
{
    //Instance of the MockMvc Object
    @Autowired
    private MockMvc mvc;

    //Instance of ObjectMapper Object
    @Autowired
    private ObjectMapper objectMap;

    //Mock Instance of Assigned Service Service Object
    @MockBean
    AssignedServiceService assignedServiceService;

    //Mock Instance of Person Service Object
    @MockBean
    PersonService personService;

    //Error message string for no Assigned Service
    private final String noAssigned = "No Assigned Service Objects";

    //Tests that an Assigned Service object is created when assigned service object is valid
    @Test
    public void givenValidAssignedService_whenPost_thenCreated() throws Exception
    {
        AssignedService assignedService = new AssignedService(1L, 1L, "emp1");
        when(assignedServiceService.addAssignedService(any(AssignedService.class))).
                thenReturn(assignedService);
        mvc.perform(post("/api/assignService/add")
                .content(objectMap.writeValueAsString(assignedService))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    //Tests that a duplicate assigned service cannot be added again
    @Test
    public void givenDuplicateAssignedService_whenPost_thenConflict() throws Exception
    {
        AssignedService assignedService1 = new AssignedService(1L, 1L, "emp1");

        String noCreate = "Assigned Service Object Could Not Be Created";
        given(assignedServiceService.addAssignedService(assignedService1)).willReturn(null);

        mvc.perform(post("/api/assignService/add")
                .content(objectMap.writeValueAsString(assignedService1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",is(noCreate)))
                .andExpect(status().isConflict());
    }

    //Tests that an Assigned Service object with null fields cannot be added
    @Test
    public void givenInvalidAssignedService_whenPost_thenBadRequest() throws Exception
    {
        AssignedService assignedService = new AssignedService(1L, 1L, null);
        String invalidObj = "Invalid Assigned Service Object";

        mvc.perform(post("/api/assignService/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(assignedService)))
                .andExpect(jsonPath("$",is(invalidObj)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a list with at least one Assigned Service is returned successfully
    @Test
    public void givenAssignedServiceList_whenGet_thenOk() throws Exception
    {
        List<AssignedService> assignedServices = new ArrayList<>();
        AssignedService assignedService = new AssignedService(1L, 1L, "emp1");
        assignedServices.add(assignedService);

        given(assignedServiceService.getAllAssignedServices()).willReturn(assignedServices);

        mvc.perform(get("/api/assignService/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //This tests that an error is returned when no Assigned Service objects exist
    @Test
    public void givenEmptyList_whenGet_thenNotFound() throws Exception
    {
        mvc.perform(get("/api/assignService/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",is(noAssigned)))
                .andExpect(status().isNotFound());

    }

    //This tests that a list is returned when an Employee has assigned services
    @Test
    public void givenValidEmployee_whenGet_thenOk() throws Exception
    {
        AssignedService assignedService = new AssignedService(1L, 1L, "emp1");
        List<AssignedService> assignedServices = new ArrayList<>();
        assignedServices.add(assignedService);

        given(assignedServiceService.getAssignedServicesByUserName(any(String.class)))
                .willReturn(assignedServices);

        mvc.perform(get("/api/assignService/service-list/{userName}", "emp1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //This tests that an error is returned when an Employee has no assigned services
    @Test
    public void givenInvalidEmployee_whenGet_thenNotFound() throws Exception
    {
        mvc.perform(get("/api/assignService/service-list/{userName}", "emp1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",is(noAssigned)))
                .andExpect(status().isNotFound());
    }

    //This tests that a list is returned when an assigned service has employees assigned
    @Test
    public void givenValidServiceID_whenGet_thenOk() throws Exception
    {
        Person person = new Person(1L,"emp304", "password",
                "Emp", "Loyee", "123 Joke Street", "0123456789");
        person.setUserType("employee");
        List<Person> people = new ArrayList<>();
        people.add(person);

        given(assignedServiceService.getAllEmployeesByAssignedService(any(Long.class)))
                .willReturn(people);

        mvc.perform(get("/api/assignService/employee-list/{serviceId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //This tests that an error is returned when an assigned service has no employees
    @Test
    public void givenInvalidServiceID_whenGet_thenNotFound() throws Exception
    {
        String noObjects = "No Employee Objects";

        mvc.perform(get("/api/assignService/employee-list/{serviceId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",is(noObjects)))
                .andExpect(status().isNotFound());
    }
}