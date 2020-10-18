package com.rmit.sept.turtorial.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.Service;
import com.rmit.sept.turtorial.demo.services.ServiceService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//This class contains tests for the Service Controller class
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerTest
{
    //Instance of the MockMvc Object
    @Autowired
    private MockMvc mvc;

    //Instance of ObjectMapper Object
    @Autowired
    private ObjectMapper objectMap;

    //Mock Instance of Service Service Object
    @MockBean
    private ServiceService serviceService;

    //Error message for Invalid Service Object
    private final String invalidService = "Invalid Service Object";

    //Tests that a valid Service can be added
    @Test
    public void givenValidService_WhenPost_isCreated() throws Exception
    {
        Service service = new Service(1L, "Simple Cut",
                "A simple hair cut for male or female", 100);
        when(serviceService.addService(any(Service.class))).
                thenReturn(service);
        mvc.perform(post("/api/service/add")
                .content(objectMap.writeValueAsString(service))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    //Tests that a service with invalid fields cannot be added
    @Test
    public void givenInvalidService_whenPost_isBadRequest() throws Exception
    {
        Service service = new Service(1L, "Simple Cut",
                null, 100);
        when(serviceService.addService(service)).
                thenReturn(null);

        mvc.perform(post("/api/service/add")
                .content(objectMap.writeValueAsString(invalidService))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //Tests that a list of no services returns an error
    @Test
    public void givenNoServices_whenGet_isNotFound() throws Exception
    {
        String noServices = "No Service Objects";

        mvc.perform(get("/api/service/list")
                .content(objectMap.writeValueAsString(noServices))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    //Tests that a if services exist, a list of services is returned
    @Test
    public void givenServices_whenGet_isOk() throws Exception
    {
        List<Service> services = new ArrayList<>();
        Service service = new Service(1L, "Simple Cut",
                null, 100);
        services.add(service);
        when(serviceService.getAllServices()).thenReturn(services);

        mvc.perform(get("/api/service/list")
                .content(objectMap.writeValueAsString(services))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests that a service cannot be updated with invalid fields
    @Test
    public void givenInvalidInput_whenUpdate_isBadRequest() throws Exception
    {
        Service service = new Service(1L, "Simple Cut",
                "A simple hair cut for male or female", 100);
        Service service1 = new Service(1L, "Simple Cut - Male",
                null, 100);
        when(serviceService.updateService(service)).thenReturn(service1);

        mvc.perform(put("/api/service/update")
                .content(objectMap.writeValueAsString(invalidService))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}