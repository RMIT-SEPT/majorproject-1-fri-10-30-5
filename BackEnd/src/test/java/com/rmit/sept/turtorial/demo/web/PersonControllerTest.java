package com.rmit.sept.turtorial.demo.web;



import com.rmit.sept.turtorial.demo.model.Person;
import com.rmit.sept.turtorial.demo.services.PersonService;
import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


@RunWith(SpringRunner.class)
//@WebMvcTest(PersonController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest
{

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMap;

    @MockBean
    private PersonService personService;

    //Error message strings
    final String invalidPerson = "Invalid Person Object";


    //return created status, when new person is valid
    @Test
    public void givenValidPerson_whenPostPerson_thenSuccessfullyCreated() throws Exception {
        Person person1 = new Person(1L,"admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        person1.setAdminCheck(true);

        when(personService.addPerson(any(Person.class))).thenReturn(person1);
        mvc.perform(post("/api/person/admin/add/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(person1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName",is("admin304")))
                .andExpect(jsonPath("$.password",is("password")))
                .andExpect(jsonPath("$.firstName",is("Admin")))
                .andExpect(jsonPath("$.lastName",is("AdminLast")))
                .andExpect(jsonPath("$.address",is("13 Fitz Street")))
                .andExpect(jsonPath("$.phone",is("0123456789")));

    }

    //Tests that a person with a 4 character password can log in
    @Test
    public void given4CharPassword_whenPostPerson_thenSuccessfullyPost() throws Exception
    {
        //Mock person object to mock correct user entry
        Person mockPerson = new Person(1L,"cust1", "pass",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);
        when(personService.addPerson(any(Person.class))).thenReturn(mockPerson);

        //Mock request valid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(status().isCreated());
    }

    //Tests that a person with a password of 25 characters can log in
    @Test
    public void given25CharPassword_whenPostPerson_thenSuccessfullyPost() throws Exception
    {
        //Mock person object to mock correct user entry
        Person mockPerson = new Person( 1L,"cust1", "passwordpasswordpasswordp",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);
        when(personService.addPerson(any(Person.class))).thenReturn(mockPerson);

        //Mock request valid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(status().isCreated());
    }

    //Tests that a person with a NULL username cannot log in
    @Test
    public void givenNULLUsername_whenPostPerson_thenReturnError() throws Exception
    {
        //Mock person object with null username
        Person mockPerson = new Person(1L, null, "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for null person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //return bad request when attempting to create person with null lastName
    @Test
    public void givenNullPersonLastName_whenPostPerson_thenBadRequest() throws Exception {
        Person person1 = new Person(1L,"admin304", "password", "Admin", null, "13 Fitz Street", "0123456789");
        String invalidPerson = "Invalid Person Object";
        person1.setAdminCheck(true);

        when(personService.addPerson(person1)).thenReturn(null);
        mvc.perform(post("/api/person/admin/add/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(person1)))
                .andExpect(jsonPath("$",is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a NULL first name cannot log in
    @Test
    public void givenNULLFirstName_whenPostPerson_thenReturnError() throws Exception
    {
        //Mock person object with null first name
        Person mockPerson = new Person(1L,"cust1", "password",
                null, "Omer", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for null person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a NULL address cannot log in
    @Test
    public void givenNULLAddress_whenPostPerson_thenReturnError() throws Exception
    {
        //Mock person object with null address
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "Omer", null, "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for null person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a NULL phone cannot log in
    @Test
    public void givenNULLPhone_whenPostPerson_thenReturnError() throws Exception
    {
        //Mock person object with null phone
        Person mockPerson = new Person(1L, "cust1", "password",
                null, "Omer", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for null person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank username cannot log in
    @Test
    public void givenBlankUserName_whenPostPerson_thenReturnError() throws Exception
    {
        //Mock person object with blank username
        Person mockPerson = new Person(1L,"", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid customer
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank password cannot log in
    @Test
    public void givenBlankPassword_whenPostPerson_thenReturnError() throws Exception
    {
        //Mock person object with blank password
        Person mockPerson = new Person(1L,"cust1", "",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank first name cannot log in
    @Test
    public void givenBlankFirstName_whenPostPerson_thenReturnError() throws Exception
    {
        //Mock person object with blank first name
        Person mockPerson = new Person(1L,"cust1", "password",
                "", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank last name cannot log in
    @Test
    public void givenBlankLastName_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock person object with blank last name
        Person mockPerson = new Person(1L,"cust1", "password",
                "Cust", "", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank address cannot log in
    @Test
    public void givenBlankAddress_whenPostPerson_thenReturnError() throws Exception
    {
        //Mock person object with blank address
        Person mockPerson = new Person(1L,"cust1", "password",
                "Cust", "Omer", "", "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for null invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank phone cannot log in
    @Test
    public void givenBlankPhone_whenPostPerson_thenReturnError() throws Exception
    {
        //Mock person object with blank phone
        Person mockPerson = new Person(1L,"cust1", "password",
                "Cust", "Omer", "123 Joke Street", "");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //return bad request when attempting to create person with password less than 4 characters
    @Test
    public void givenInvalidPersonPassword_whenPostPerson_thenBadRequest() throws Exception {
        Person person1 = new Person(1L,"admin304", "pas", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        String invalidPerson = "Invalid Person Object";
        person1.setAdminCheck(true);

        when(personService.addPerson(person1)).thenReturn(null);
        mvc.perform(post("/api/person/admin/add/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(person1)))
                .andExpect(jsonPath("$",is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a 26 character password cannot log in
    @Test
    public void given26CharPassword_whenPostPerson_thenReturnError() throws Exception
    {
        ///Invalid Mock person object with 26 char password
        Person mockPerson = new Person(1L,"cust1", "passwordpasswordpasswordpp",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is("Invalid Person Object")))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a 9 character phone cannot log in
    @Test
    public void given9CharPhone_whenPostPerson_thenReturnError() throws Exception
    {
        ///Invalid Mock person object with 9 char phone
        Person mockPerson = new Person(1L,"cust1", "password",
                "Cust", "Omer", "123 Joke Street", "041234567");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with an 11 character phone cannot log in
    @Test
    public void given11CharPhone_whenPostPerson_thenReturnError() throws Exception
    {
        //Invalid Mock person object with 11 char phone
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "Omer", "123 Joke Street", "04123456789");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a invalid characters in their first name cannot log in
    @Test
    public void givenInvalidFirstnameFormat_whenPostPerson_thenReturnError() throws Exception
    {
        //Invalid Mock person object with invalid characters in first name
        Person mockPerson = new Person(1L, "cust1", "password",
                "26283:", "Omer", "123 Joke Street", "04123456789");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a invalid characters in their last name cannot log in
    @Test
    public void givenInvalidLastnameFormat_whenPostPerson_thenReturnError() throws Exception
    {
        //Invalid Mock person object with invalid characters in first name
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "384%^&", "123 Joke Street", "04123456789");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a invalid characters in their phone cannot log in
    @Test
    public void givenInvalidPhoneFormat_whenPostPerson_thenReturnError() throws Exception
    {
        //Invalid Mock person object with invalid characters in first name
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "Omer", "123 Joke Street", "AKSJAKSLOW");
        mockPerson.setCustomerCheck(true);

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //return ok status, when updated admin is valid
    @Test
    public void givenValidAdmin_whenUpdateAdmin_thenSuccessfullyOk() throws Exception
    {
        //   Admin admin1 = new Admin("admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        Person admin1 = new Person(1L,"admin304", "password123", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        admin1.setAdminCheck(true);

        when(personService.updatePerson(any(Person.class))).thenReturn(admin1);

        mvc.perform(put("/api/person/admin/update").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(admin1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
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
        Person admin1 = new Person(1L,"admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        Person newAdmin = new Person(1L,"admin304", null, "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        String invalidPerson = "Invalid Person Object";
        newAdmin.setAdminCheck(true);

        when(personService.updatePerson(admin1)).thenReturn(newAdmin);
        mvc.perform(put("/api/person/admin/update").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(newAdmin)))
                .andExpect(jsonPath("$",is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }


    //return accepted status, when deleting an existing admin
    @Test
    public void givenExistingPerson_whenDeletePerson_thenSuccessfullyAccepted() throws Exception {
        Person admin1 = new Person(1L,"admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        String message = "Person " + admin1.getUserName() + " has been successfully removed";
        admin1.setAdminCheck(true);

        when(personService.deletePerson(admin1.getUserName())).thenReturn(message);
        mvc.perform(delete("/api/person/admin/delete/{userName}", "admin304").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(admin1)))
                .andExpect(jsonPath("$",is(message)))
                .andExpect(status().isAccepted());
    }

    //return accepted status, when deleting an existing admin
    @Test
    public void givenInvalidPerson_whenDeletePerson_thenReturnNotFound() throws Exception {
        String message = "No Person Object";

        when(personService.deletePerson("admin30")).thenReturn(null);
        mvc.perform(delete("/api/person/admin/delete/{userName}", "admin30").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",is(message)))
                .andExpect(status().isNotFound());
    }


    //  return not found status, when attempting to delete an person that doesn't exist
    @Test
    public void givenValidAdminUserName_whenGetAdmin_thenReturnPerson() throws Exception {
        Person admin1 = new Person(1L,"admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");

        when(personService.getPersonByUserName(admin1.getUserName())).thenReturn(admin1);
        mvc.perform(get("/api/person/admin/{userName}", "admin304").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(admin1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.userName",is("admin304")))
                .andExpect(jsonPath("$.password",is("password")))
                .andExpect(jsonPath("$.firstName",is("Admin")))
                .andExpect(jsonPath("$.lastName",is("AdminLast")))
                .andExpect(jsonPath("$.address",is("13 Fitz Street")))
                .andExpect(jsonPath("$.phone",is("0123456789")));

    }

    //  return not found status, when searching for an admin that doesn't exist
    @Test
    public void givenInvalidAdminUserName_whenGetAdmin_thenReturnNotFound() throws Exception {
        String message = "No Person Object";

        when(personService.getPersonByUserName("admin30")).thenReturn(null);
        mvc.perform(get("/api/person/admin/{userName}", "admin30").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",is(message)))
                .andExpect(status().isNotFound());

    }

    //Tests that a customer that exists can't be updated with inappropriate data
    @Test
    public void givenInvalidCustomer_whenUpdateCustomer_thenReturnError() throws Exception
    {
        //Mock customer objects, one with invalid parameters
        Person mockCustomer = new Person(1L, "cust1", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        Person updMockCustomer = new Person(1L, "cust1", "password",
                "Cust", "Omer", null, "0412345678");

        when(personService.updatePerson(updMockCustomer)).thenReturn(updMockCustomer);

        //Mock request for invalid customer
        mvc.perform(put("/api/person/customer/update/", mockCustomer.getUserName()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(updMockCustomer)))
                .andExpect(jsonPath("$",is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer that exists can be deleted
    @Test
    public void givenExistingCustomer_whenDeleteCustomer_thenSuccessfullyDelete() throws Exception
    {
        Person mockCustomer = new Person(1L, "cust1", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        String delPerson= "Person " + mockCustomer.getUserName() + " has been successfully removed";
        mockCustomer.setCustomerCheck(true);

        given(this.personService.deletePerson(mockCustomer.getUserName())).willReturn(delPerson);

        mvc.perform(delete("/api/person/customer/delete/{userName}", "cust1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$",is(delPerson)))
                .andExpect(status().isAccepted());
    }

    //Tests that a customer that exists can be retrieved using the GET method
    @Test
    public void givenValidUserName_whenGetCustomer_thenReturnCustomer() throws Exception
    {
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");

        given(personService.getPersonByUserName("cust1")).willReturn(mockPerson);

        mvc.perform(get("/api/person/customer/{userName}", "cust1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(status().isOk());
    }

    //Tests that an employee that exists can't be updated with inappropriate data
    @Test
    public void givenInvalidEmployee_whenUpdateEmployee_thenReturnError() throws Exception
    {
        //Mock Employee object with null
        Person mockEmployee = new Person(1L, "emp", "password",
                "Emp", "Loyee", "45 Joke Street", "0412345678");
        Person updMockEmployee = new Person(1L, "emp", "password",
                "Emp", "Loyee", null, "0412345678");

        when(personService.updatePerson(updMockEmployee)).thenReturn(updMockEmployee);

        //Mock request for invalid employee
        mvc.perform(put("/api/person/employee/update/", mockEmployee.getUserName()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(updMockEmployee)))
                .andExpect(jsonPath("$",is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee that exists can be deleted
    @Test
    public void givenExistingEmp_whenDeleteEmp_thenSuccessfullyDelete() throws Exception
    {
        Person mockEmployee = new Person(1L, "emp", "password",
                "Emp", "Loyee", "123 Joke Street", "0412345678");
        String delEmployee = "Person " + mockEmployee.getUserName() + " has been successfully removed";

        given(this.personService.deletePerson(mockEmployee.getUserName())).willReturn(delEmployee);

        mvc.perform(delete("/api/person/employee/delete/{userName}", "emp").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$",is(delEmployee)))
                .andExpect(status().isAccepted());
    }

    //Tests that an employee that exists can be retrieved using the GET method
    @Test
    public void givenValidUserName_whenGetEmp_thenReturnEmp() throws Exception
    {
        Person mockEmployee = new Person(1L,"emp", "password",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        given(personService.getPersonByUserName("emp")).willReturn(mockEmployee);

        mvc.perform(get("/api/person/employee/{userName}", "emp").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(status().isOk());
    }
}
//
////    //return conflict when attempting to update admin that doesn't exist
////    @Test
////    public void givenInvalidAdmin_whenUpdateAdmin_thenConflict() throws Exception {
////        Admin newAdmin = new Admin("admin304", "password", "Admin", "AdminLast", "13 Fitz Street", "0123456789");
////        String invalidAdmin = "Admin Object Could Not Be Updated";
////
////        when(adminService.updateAdmin(newAdmin)).thenReturn(newAdmin);
////        mvc.perform(put("/api/admin/update").contentType(MediaType.APPLICATION_JSON)
////                .accept(MediaType.APPLICATION_JSON)
////                .content(objectMap.writeValueAsString(newAdmin)))
////                .andExpect(jsonPath("$",is(invalidAdmin)))
////                .andExpect(status().isConflict());
////    }
