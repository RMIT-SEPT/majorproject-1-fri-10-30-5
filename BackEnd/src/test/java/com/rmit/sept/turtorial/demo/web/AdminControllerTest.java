package com.rmit.sept.turtorial.demo.web;



import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.Admin;
import com.rmit.sept.turtorial.demo.services.AdminService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMap;

    @MockBean
    private AdminService adminService;


    //return created status, when new admin is valid
    @Test
    public void givenValidAdmin_whenPostAdmin_thenSuccessfullyCreated() throws Exception {
        Admin admin1 = new Admin("admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");

        when(adminService.addAdmin(any(Admin.class))).thenReturn(admin1);
        mvc.perform(post("/api/admin/add/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(admin1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName",is("admin304")))
                .andExpect(jsonPath("$.password",is("password")))
                .andExpect(jsonPath("$.firstName",is("Admin")))
                .andExpect(jsonPath("$.lastName",is("AdminLast")))
                .andExpect(jsonPath("$.address",is("13 Fitz Street")))
                .andExpect(jsonPath("$.phone",is("0123456789")));

    }


    //return bad request when attempting to create admin with password less than 4 characters
    @Test
    public void givenInvalidAdminPassword_whenPostAdmin_thenBadRequest() throws Exception {
        Admin admin1 = new Admin("admin304", "pas", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        String invalidAdmin = "Invalid Admin Object";

        when(adminService.addAdmin(admin1)).thenReturn(null);
        mvc.perform(post("/api/admin/add/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(admin1)))
                .andExpect(jsonPath("$",is(invalidAdmin)))
                .andExpect(status().isBadRequest());
    }


    //return bad request when attempting to create admin with null lastName
    @Test
    public void givenNullAdminLastName_whenPostAdmin_thenBadRequest() throws Exception {
        Admin admin1 = new Admin("admin304", "password", "Admin", null, "13 Fitz Street", "0123456789");
        String invalidAdmin = "Invalid Admin Object";

        when(adminService.addAdmin(admin1)).thenReturn(null);
        mvc.perform(post("/api/admin/add/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(admin1)))
                .andExpect(jsonPath("$",is(invalidAdmin)))
                .andExpect(status().isBadRequest());
    }


    //return ok status, when updated admin is valid
    @Test
    public void givenValidAdmin_whenUpdateAdmin_thenSuccessfullyOk() throws Exception {
     //   Admin admin1 = new Admin("admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        Admin newAdmin = new Admin("admin304", "password123", "Admin", "AdminLast", "13 Fitz Street", "0123456789");

        when(adminService.updateAdmin(any(Admin.class))).thenReturn(newAdmin);

        mvc.perform(put("/api/admin/update").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(newAdmin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName",is("admin304")))
                .andExpect(jsonPath("$.password",is("password123")))
                .andExpect(jsonPath("$.firstName",is("Admin")))
                .andExpect(jsonPath("$.lastName",is("AdminLast")))
                .andExpect(jsonPath("$.address",is("13 Fitz Street")))
                .andExpect(jsonPath("$.phone",is("0123456789")));
    }


    //return bad request when attempting to update admin with null password
    @Test
    public void givenNullAdminPassword_whenUpdateAdmin_thenBadRequest() throws Exception {
        Admin admin1 = new Admin("admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        Admin newAdmin = new Admin("admin304", null, "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        String invalidAdmin = "Invalid Admin Object";

        when(adminService.updateAdmin(newAdmin)).thenReturn(newAdmin);
        mvc.perform(put("/api/admin/update").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(newAdmin)))
                .andExpect(jsonPath("$",is(invalidAdmin)))
                .andExpect(status().isBadRequest());
    }


    //return accepted status, when deleting an existing admin
    @Test
    public void givenExistingAdmin_whenDeleteAdmin_thenSuccessfullyAccepted() throws Exception {
        Admin admin1 = new Admin("admin30", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        String message = "Admin " + admin1.getUserName() + " has been successfully removed";

        when(adminService.deleteAdmin(admin1.getUserName())).thenReturn(message);
        mvc.perform(delete("/api/admin/delete/{userName}", "admin30").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(admin1)))
                .andExpect(jsonPath("$",is(message)))
                .andExpect(status().isAccepted());
    }

    //return accepted status, when deleting an existing admin
    @Test
    public void givenInvalidAdmin_whenDeleteAdmin_thenReturnNotFound() throws Exception {
        String message = "No Admin Object";

        when(adminService.deleteAdmin("admin30")).thenReturn(null);
        mvc.perform(delete("/api/admin/delete/{userName}", "admin30").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",is(message)))
                .andExpect(status().isNotFound());
    }


    //  return not found status, when attempting to delete an admin that doesn't exist
    @Test
    public void givenValidAdminUserName_whenGetAdmin_thenReturnAdmin() throws Exception {
        Admin admin1 = new Admin("admin30", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");

        when(adminService.getAdminByUserName(admin1.getUserName())).thenReturn(admin1);
        mvc.perform(get("/api/admin/{userName}", "admin30").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(admin1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName",is("admin30")))
                .andExpect(jsonPath("$.password",is("password")))
                .andExpect(jsonPath("$.firstName",is("Admin")))
                .andExpect(jsonPath("$.lastName",is("AdminLast")))
                .andExpect(jsonPath("$.address",is("13 Fitz Street")))
                .andExpect(jsonPath("$.phone",is("0123456789")));

    }

  //  return not found status, when searching for an admin that doesn't exist
    @Test
    public void givenInvalidAdminUserName_whenGetAdmin_thenReturnNotFound() throws Exception {
        String message = "No Admin Object";

        when(adminService.getAdminByUserName("admin30")).thenReturn(null);
        mvc.perform(get("/api/admin/{userName}", "admin30").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",is(message)))
                .andExpect(status().isNotFound());

    }






}

//    //return conflict when attempting to update admin that doesn't exist
//    @Test
//    public void givenInvalidAdmin_whenUpdateAdmin_thenConflict() throws Exception {
//        Admin newAdmin = new Admin("admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
//        String invalidAdmin = "Admin Object Could Not Be Updated";
//
//        when(adminService.updateAdmin(newAdmin)).thenReturn(newAdmin);
//        mvc.perform(put("/api/admin/update").contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMap.writeValueAsString(newAdmin)))
//                .andExpect(jsonPath("$",is(invalidAdmin)))
//                .andExpect(status().isConflict());
//    }







