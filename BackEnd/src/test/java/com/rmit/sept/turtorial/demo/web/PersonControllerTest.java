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

//This class contains all tests for the Person Controller class.
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest
{
    //Instance of MockMvc Object
    @Autowired
    private MockMvc mvc;

    //Instance of an ObjectMapper Object
    @Autowired
    private ObjectMapper objectMap;

    //Instance of a Person Service Object
    @MockBean
    private PersonService personService;

    //Error message strings
    private final String invalidPerson = "Invalid Person Object";


    //Tests that a valid person object can be posted
    @Test
    public void givenValidPerson_whenPostPerson_thenCreated() throws Exception
    {
        Person person1 = new Person(1L,"admin304", "password",
                "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        person1.setUserType("admin");

        when(personService.addPerson(any(Person.class))).thenReturn(person1);
        mvc.perform(post("/api/person/add").contentType(MediaType.APPLICATION_JSON)
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

    //Tests that a person with a 4 character password can be posted
    @Test
    public void given4CharPassword_whenPostPerson_thenCreated() throws Exception
    {
        //Mock person object to mock correct user entry
        Person mockPerson = new Person(1L,"cust1", "pass",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");
        when(personService.addPerson(any(Person.class))).thenReturn(mockPerson);

        //Mock request valid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(status().isCreated());
    }

    //Tests that a person with a password of 25 characters can be posted
    @Test
    public void given25CharPassword_whenPostPerson_thenCreated() throws Exception
    {
        //Mock person object to mock correct user entry
        Person mockPerson = new Person( 1L,"cust1",
                "passwordpasswordpasswordp", "Cust", "Omer",
                "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");
        when(personService.addPerson(any(Person.class))).thenReturn(mockPerson);

        //Mock request valid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(status().isCreated());
    }

    //Tests that a person with a NULL username cannot be posted
    @Test
    public void givenNULLUsername_whenPostPerson_thenBadRequest() throws Exception
    {
        //Mock person object with null username
        Person mockPerson = new Person(1L, null, "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for null person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a NULL first name cannot be posted
    @Test
    public void givenNULLFirstName_whenPostPerson_thenBadRequest() throws Exception
    {
        //Mock person object with null first name
        Person mockPerson = new Person(1L,"cust1", "password",
                null, "Omer", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for null person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a NULL lastName cannot be posted
    @Test
    public void givenNullPersonLastName_whenPostPerson_thenBadRequest() throws Exception
    {
        Person person1 = new Person(1L,"admin304", "password",
                "Admin", null, "13 Fitz Street", "0123456789");
        person1.setUserType("admin");

        when(personService.addPerson(person1)).thenReturn(null);
        mvc.perform(post("/api/person/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(person1)))
                .andExpect(jsonPath("$",is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a NULL address cannot be posted
    @Test
    public void givenNULLAddress_whenPostPerson_thenBadRequest() throws Exception
    {
        //Mock person object with null address
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "Omer", null, "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for null person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a NULL phone cannot be posted
    @Test
    public void givenNULLPhone_whenPostPerson_thenBadRequest() throws Exception
    {
        //Mock person object with null phone
        Person mockPerson = new Person(1L, "cust1", "password",
                null, "Omer", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for null person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank username cannot be posted
    @Test
    public void givenBlankUserName_whenPostPerson_thenBadRequest() throws Exception
    {
        //Mock person object with blank username
        Person mockPerson = new Person(1L,"", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for invalid customer
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank password cannot be posted
    @Test
    public void givenBlankPassword_whenPostPerson_thenBadRequest() throws Exception
    {
        //Mock person object with blank password
        Person mockPerson = new Person(1L,"cust1", "",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank first name cannot be posted
    @Test
    public void givenBlankFirstName_whenPostPerson_thenBadRequest() throws Exception
    {
        //Mock person object with blank first name
        Person mockPerson = new Person(1L,"cust1", "password",
                "", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank last name cannot be posted
    @Test
    public void givenBlankLastName_whenPostCustomer_thenBadRequest() throws Exception
    {
        //Mock person object with blank last name
        Person mockPerson = new Person(1L,"cust1", "password",
                "Cust", "", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank address cannot be posted
    @Test
    public void givenBlankAddress_whenPostPerson_thenBadRequest() throws Exception
    {
        //Mock person object with blank address
        Person mockPerson = new Person(1L,"cust1", "password",
                "Cust", "Omer", "", "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for null invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a blank phone cannot be posted
    @Test
    public void givenBlankPhone_whenPostPerson_thenBadRequest() throws Exception
    {
        //Mock person object with blank phone
        Person mockPerson = new Person(1L,"cust1", "password",
                "Cust", "Omer", "123 Joke Street", "");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a 4 character password cannot be posted
    @Test
    public void givenInvalidPersonPassword_whenPostPerson_thenBadRequest() throws Exception
    {
        Person person1 = new Person(1L,"admin304", "pas",
                "Admin", "AdminLast", "13 Fitz Street",
                "0123456789");
        person1.setUserType("admin");

        when(personService.addPerson(person1)).thenReturn(null);
        mvc.perform(post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(person1)))
                .andExpect(jsonPath("$",is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a 26 character password cannot be posted
    @Test
    public void given26CharPassword_whenPostPerson_thenBadRequest() throws Exception
    {
        ///Invalid Mock person object with 26 char password
        Person mockPerson = new Person(1L,"cust1", "passwordpasswordpasswordpp",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a 9 character phone cannot be posted
    @Test
    public void given9CharPhone_whenPostPerson_thenBadRequest() throws Exception
    {
        ///Invalid Mock person object with 9 char phone
        Person mockPerson = new Person(1L,"cust1", "password",
                "Cust", "Omer", "123 Joke Street", "041234567");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with an 11 character phone cannot be posted
    @Test
    public void given11CharPhone_whenPostPerson_thenBadRequest() throws Exception
    {
        //Invalid Mock person object with 11 char phone
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "Omer", "123 Joke Street", "04123456789");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a invalid characters in their first name cannot be posted
    @Test
    public void givenInvalidFirstnameFormat_whenPostPerson_thenBadRequest() throws Exception
    {
        //Invalid Mock person object with invalid characters in first name
        Person mockPerson = new Person(1L, "cust1", "password",
                "26283:", "Omer", "123 Joke Street", "04123456789");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a invalid characters in their last name cannot be posted
    @Test
    public void givenInvalidLastnameFormat_whenPostPerson_thenBadRequest() throws Exception
    {
        //Invalid Mock person object with invalid characters in first name
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "384%^&", "123 Joke Street", "04123456789");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a person with a invalid characters in their phone cannot be posted
    @Test
    public void givenInvalidPhoneFormat_whenPostPerson_thenBadRequest() throws Exception
    {
        //Invalid Mock person object with invalid characters in first name
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "Omer", "123 Joke Street", "AKSJAKSLOW");
        mockPerson.setUserType("customer");

        //Mock request for invalid person
        mvc.perform(MockMvcRequestBuilders.post("/api/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(jsonPath("$", is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an existing admin that is validated correctly returns an OK status
    @Test
    public void givenValidAdmin_whenUpdateAdmin_thenSuccessfullyOk() throws Exception
    {
        Person admin1 = new Person(1L,"admin304", "password123",
                "Admin", "AdminLast", "13 Fitz Street", "0123456789");
        admin1.setUserType("admin");

        when(personService.updatePerson(any(Person.class))).thenReturn(admin1);

        mvc.perform(put("/api/person/update")
                .contentType(MediaType.APPLICATION_JSON)
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


    //Tests that when updating an admin with invalid fields returns a Bad Request
    @Test
    public void givenNullAdminPassword_whenUpdateAdmin_thenBadRequest() throws Exception
    {
        Person admin1 = new Person(1L,"admin304", "password", "Admin",
                "AdminLast", "13 Fitz Street", "0123456789");
        Person newAdmin = new Person(1L,"admin304", null, "Admin",
                "AdminLast", "13 Fitz Street", "0123456789");
        newAdmin.setUserType("admin");

        when(personService.updatePerson(admin1)).thenReturn(newAdmin);
        mvc.perform(put("/api/person/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(newAdmin)))
                .andExpect(jsonPath("$",is(invalidPerson)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer that exists can be retrieved using the GET method
    @Test
    public void givenValidUserName_whenGetCustomer_thenOk() throws Exception
    {
        Person mockPerson = new Person(1L, "cust1", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        mockPerson.setUserType("customer");

        given(personService.getPersonByUserName("cust1")).willReturn(mockPerson);

        mvc.perform(get("/api/person/customer/{userName}", "cust1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockPerson)))
                .andExpect(status().isOk());
    }
}