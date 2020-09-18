package com.rmit.sept.turtorial.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.Customer;
import com.rmit.sept.turtorial.demo.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest
{
    //Convert object to JSON string
    @Autowired
    private MockMvc mockMvc;

    //Convert object to JSON string
    @Autowired
    private ObjectMapper objectMap;

    //Creates mock object for CustomerService
    @MockBean
    private CustomerService customerService;

    //Error message strings
    final String invalidCust = "Invalid Customer Object";

    //Tests that  customer with valid credentials can log in
    @Test
    public void givenCustomer_whenPostCustomer_thenSuccessfullyPost() throws Exception
    {
        //Mock customer object to mock correct user entry
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        when(customerService.addCustomer(any(Customer.class))).thenReturn(mockCustomer);

        //Mock request for valid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(status().isCreated());
    }

    //Tests that a customer with a 4 character password can log in
    @Test
    public void given4CharPassword_whenPostCustomer_thenSuccessfullyPost() throws Exception
    {
        //Mock customer object to mock correct user entry
        Customer mockCustomer = new Customer("cust1", "pass",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        when(customerService.addCustomer(any(Customer.class))).thenReturn(mockCustomer);

        //Mock request valid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(status().isCreated());
    }

    //Tests that a customer with a password of 25 characters can log in
    @Test
    public void given25CharPassword_whenPostCustomer_thenSuccessfullyPost() throws Exception
    {
        //Mock customer object to mock correct user entry
        Customer mockCustomer = new Customer("cust1", "passwordpasswordpasswordp",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        when(customerService.addCustomer(any(Customer.class))).thenReturn(mockCustomer);

        //Mock request valid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(status().isCreated());
    }

    //Tests that a customer with a NULL username cannot log in
    @Test
    public void givenNULLUsername_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with null username
        Customer mockCustomer = new Customer(null, "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");

        //Mock request for null customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a NULL password cannot log in
    @Test
    public void givenNULLPassword_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with null password
        Customer mockCustomer = new Customer("cust1", null, "Cust",
                "Omer", "123 Joke Street", "0412345678");

        //Mock request for null customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a NULL first name cannot log in
    @Test
    public void givenNULLFirstName_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with null first name
        Customer mockCustomer = new Customer("cust1", "password",
                null, "Omer", "123 Joke Street", "0412345678");

        //Mock request for null customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a NULL last name cannot log in
    @Test
    public void givenNULLLastName_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with null last name
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", null, "123 Joke Street", "0412345678");

        //Mock request for null customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a NULL address cannot log in
    @Test
    public void givenNULLAddress_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with null address
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", null, "0412345678");

        //Mock request for null customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a NULL phone cannot log in
    @Test
    public void givenNULLPhone_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with null phone
        Customer mockCustomer = new Customer("cust1", "password",
                null, "Omer", "123 Joke Street", "0412345678");

        //Mock request for null customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a blank username cannot log in
    @Test
    public void givenBlankUserName_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with blank username
        Customer mockCustomer = new Customer("", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a blank password cannot log in
    @Test
    public void givenBlankPassword_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with blank password
        Customer mockCustomer = new Customer("cust1", "",
                "Cust", "Omer", "123 Joke Street", "0412345678");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a blank first name cannot log in
    @Test
    public void givenBlankFirstName_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with blank first name
        Customer mockCustomer = new Customer("cust1", "password",
                "", "Omer", "123 Joke Street", "0412345678");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a blank last name cannot log in
    @Test
    public void givenBlankLastName_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with blank last name
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "", "123 Joke Street", "0412345678");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a blank address cannot log in
    @Test
    public void givenBlankAddress_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with blank address
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", "", "0412345678");

        //Mock request for null invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a blank phone cannot log in
    @Test
    public void givenBlankPhone_whenPostCustomer_thenReturnError() throws Exception
    {
        //Mock customer object with blank phone
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", "123 Joke Street", "");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a 3 character password cannot log in
    @Test
    public void given3CharPassword_whenPostCustomer_thenReturnError() throws Exception
    {
        ///Invalid Mock customer object with 3 char password
        Customer mockCustomer = new Customer("cust1", "pwd",
                "Cust", "Omer", "123 Joke Street", "0412345678");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a 26 character password cannot log in
    @Test
    public void given26CharPassword_whenPostCustomer_thenReturnError() throws Exception
    {
        ///Invalid Mock customer object with 25 char password
        Customer mockCustomer = new Customer("cust1", "passwordpasswordpasswordpp",
                "Cust", "Omer", "123 Joke Street", "0412345678");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is("Invalid Customer Object")))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a 9 character phone cannot log in
    @Test
    public void given9CharPhone_whenPostCustomer_thenReturnError() throws Exception
    {
        ///Invalid Mock customer object with 9 char phone
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", "123 Joke Street", "041234567");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with an 11 character phone cannot log in
    @Test
    public void given11CharPhone_whenPostCustomer_thenReturnError() throws Exception
    {
        //Invalid Mock customer object with 11 char phone
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", "123 Joke Street", "04123456789");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a invalid characters in their first name cannot log in
    @Test
    public void givenInvalidFirstnameFormat_whenPostCustomer_thenReturnError() throws Exception
    {
        //Invalid Mock customer object with invalid characters in first name
        Customer mockCustomer = new Customer("cust1", "password",
                "26283:", "Omer", "123 Joke Street", "04123456789");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a invalid characters in their last name cannot log in
    @Test
    public void givenInvalidLastnameFormat_whenPostCustomer_thenReturnError() throws Exception
    {
        //Invalid Mock customer object with invalid characters in first name
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "384%^&", "123 Joke Street", "04123456789");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer with a invalid characters in their phone cannot log in
    @Test
    public void givenInvalidPhoneFormat_whenPostCustomer_thenReturnError() throws Exception
    {
        //Invalid Mock customer object with invalid characters in first name
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", "123 Joke Street", "AKSJAKSLOW");

        //Mock request for invalid customer
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$", is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer that exists can't be updated with inappropriate data
    @Test
    public void givenInvalidCustomer_whenUpdateCustomer_thenReturnError() throws Exception
    {
        //Mock customer objects, one with invalid parameters
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        Customer updMockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", null, "0412345678");

        when(customerService.updateCustomer(updMockCustomer)).thenReturn(updMockCustomer);

        //Mock request for invalid customer
        mockMvc.perform(put("/api/customer/update/", mockCustomer.getUserName()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(updMockCustomer)))
                .andExpect(jsonPath("$",is(invalidCust)))
                .andExpect(status().isBadRequest());
    }

    //Tests that a customer that exists can be deleted
    @Test
    public void givenExistingCust_whenDeleteCust_thenSuccessfullyDelete() throws Exception
    {
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");
        String delCustomer= "Customer " + mockCustomer.getUserName() + " has been successfully removed";

        given(this.customerService.deleteCustomer(mockCustomer.getUserName())).willReturn(delCustomer);

        mockMvc.perform(delete("/api/customer/delete/{userName}", "cust1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(jsonPath("$",is(delCustomer)))
                .andExpect(status().isAccepted());
    }

    //Tests that a customer that exists can be retrieved using the GET method
    @Test
    public void givenValidUserName_whenGetCust_thenReturnCust() throws Exception
    {
        Customer mockCustomer = new Customer("cust1", "password",
                "Cust", "Omer", "123 Joke Street", "0412345678");

        given(customerService.getCustomerByUserName("cust1")).willReturn(mockCustomer);

        mockMvc.perform(get("/api/customer/{userName}", "cust1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockCustomer)))
                .andExpect(status().isOk());
    }
}
