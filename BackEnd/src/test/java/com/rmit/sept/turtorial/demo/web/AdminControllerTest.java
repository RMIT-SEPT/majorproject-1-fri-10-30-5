package com.rmit.sept.turtorial.demo.web;

import org.junit.Test;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.Admin;
import com.rmit.sept.turtorial.demo.services.AdminService;
import com.rmit.sept.turtorial.demo.web.AdminController;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



import java.util.Arrays;
import java.util.List;

//shorthand for springJunit4 class runner
//
@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    //sends post/gets to url etc.
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMap;

    //creating mock of working hour's service
    @MockBean
    private  AdminService admin;





    @Test
    public void addAdmin() throws Exception {
        Admin admin1 = new Admin("username","password","name","address","0123456789");

        mvc.perform(post("/api/admin/add/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(admin1)))
                .andExpect(status().isCreated());
        //  .andExpect(jsonPath("$.username",is(admin1.getUserName())));
    }
}