package com.rmit.sept.turtorial.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmit.sept.turtorial.demo.model.Employee;
import com.rmit.sept.turtorial.demo.services.EmployeeService;
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
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    //Convert object to JSON string
    @Autowired
    private MockMvc mockMvc;

    //Convert object to JSON string
    @Autowired
    private ObjectMapper objectMap;

    //Creates mock object for EmployeeService
    @MockBean
    private EmployeeService employeeService;

    //Error message strings
    final String invalidEmp = "Invalid Employee Object";

    //Tests that an employee with valid credentials can log in
    @Test
    public void givenEmployee_whenPostEmployee_thenSuccessfullyPost() throws Exception
    {
        //Mock Employee object to mock correct user entry
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(mockEmployee);

        //Mock request for valid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(status().isCreated());
    }

    //Tests that an employee with a password of 4 characters can log in
    @Test
    public void given4CharPassword_whenPostEmployee_thenSuccessfullyPost() throws Exception
    {
        //Mock Employee object to mock correct user entry with 4 character password
        Employee mockEmployee = new Employee("emp", "pass", "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(mockEmployee);

        //Mock request for valid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(status().isCreated());
    }

    //Tests that an employee with a password of 25 characters can log in
    @Test
    public void given25CharPassword_whenPostEmployee_thenSuccessfullyPost() throws Exception
    {
        //Mock Employee object to mock correct user entry with 25 character password
        Employee mockEmployee = new Employee("emp", "passwordpasswordpasswordp",
                "admin", "Emp", "Loyee", "123 Joke Street", "0412345678");

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(mockEmployee);

        //Mock request for valid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(status().isCreated());
    }

    //Tests that an employee with an admin that has 2 characters can log in
    @Test
    public void given2CharAdmin_whenPostEmployee_thenSuccessfullyPost() throws Exception
    {
        //Mock Employee object to mock correct user entry with a 2 character admin
        Employee mockEmployee = new Employee("emp", "password",
                "ad", "Emp", "Loyee", "123 Joke Street", "0412345678");

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(mockEmployee);

        //Mock request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(status().isCreated());
    }

    //Tests that an employee with an admin that has 20 characters can log in
    @Test
    public void given20CharAdmin_whenPostEmployee_thenSuccessfullyPost() throws Exception
    {
        //Mock Employee object to mock correct user entry with a 20 character admin
        Employee mockEmployee = new Employee("emp", "password",
                "adminadminadminadmin", "Emp", "Loyee",
                "123 Joke Street", "0412345678");

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(mockEmployee);

        //Mock request for valid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(status().isCreated());
    }

    //Tests that an employee with a NULL username cannot log in
    @Test
    public void givenNULLUsername_whenPostEmployee_thenReturnError() throws Exception
    {
        //Mock Employee object with null username
        Employee mockEmployee = new Employee(null, "password", "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for null employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a NULL password cannot log in
    @Test
    public void givenNULLPassword_whenPostEmployee_thenReturnError() throws Exception
    {
        //Mock Employee object with null password
        Employee mockEmployee = new Employee("emp", null, "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for null employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a NULL admin cannot log in
    @Test
    public void givenNULLAdmin_whenPostEmployee_thenReturnError() throws Exception
    {
        //Mock Employee object with null admin
        Employee mockEmployee = new Employee("emp", "password", null,
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for null employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a NULL first name cannot log in
    @Test
    public void givenNULLFirstName_whenPostEmployee_thenReturnError() throws Exception
    {
        //Mock Employee object with null firstname
        Employee mockEmployee = new Employee("emp", "password", "admin",
                null, "Loyee", "123 Joke Street", "0412345678");

        //Mock request for null employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a NULL last name cannot log in
    @Test
    public void givenNULLLastName_whenPostEmployee_thenReturnError() throws Exception
    {
        //Mock Employee object with null lastname
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", null, "123 Joke Street", "0412345678");

        //Mock request for null employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a NULL address cannot log in
    @Test
    public void givenNULLAddress_whenPostEmployee_thenReturnError() throws Exception
    {
        //Mock Employee object with null address
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", null, "0412345678");

        //Mock request for null employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a NULL phone cannot log in
    @Test
    public void givenNULLPhone_whenPostEmployee_thenReturnError() throws Exception
    {
        //Mock Employee object with null phone
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "123 Joke Street", null);

        //Mock request for null employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a blank username cannot log in
    @Test
    public void givenBlankUserName_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with no username
        Employee mockEmployee = new Employee("", "password", "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a blank password cannot log in
    @Test
    public void givenBlankPassword_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with no password
        Employee mockEmployee = new Employee("emp", "", "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a blank admin cannot log in
    @Test
    public void givenBlankAdmin_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with no admin
        Employee mockEmployee = new Employee("emp", "password", "",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a blank first name cannot log in
    @Test
    public void givenBlankFirstName_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with no firstname
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a blank last name cannot log in
    @Test
    public void givenBlankLastName_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with no lastname
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a blank address cannot log in
    @Test
    public void givenBlankAddress_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with no address
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a blank phone cannot log in
    @Test
    public void givenBlankPhone_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with no phone
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "123 Joke Street", "");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a 3 character password cannot log in
    @Test
    public void given3CharPassword_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with 3 character password
        Employee mockEmployee = new Employee("emp", "pas", "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a 26 character password cannot log in
    @Test
    public void given26CharPassword_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with 25 character password
        Employee mockEmployee = new Employee("emp", "passssssssssssssssssssword", "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a 1 character admin cannot log in
    @Test
    public void given1CharAdmin_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with 1 character admin
        Employee mockEmployee = new Employee("emp", "password", "a",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a 21 character admin cannot log in
    @Test
    public void given21CharAdmin_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock user entry with 21 character admin
        Employee mockEmployee = new Employee("emp", "password", "adminadminadminadmina",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a 9 character phone cannot log in
    @Test
    public void given9CharPhone_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock phone entry with 9 characters
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "123 Joke Street", "041234567");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with an 11 character phone cannot log in
    @Test
    public void given11CharPhone_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock phone entry with 11 characters
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "123 Joke Street", "04123456789");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a invalid characters in their first name cannot log in
    @Test
    public void givenInvalidFirstnameFormat_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock firstname entry with non-alphabetical characters
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "192$%", "Loyee", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a invalid characters in their last name cannot log in
    @Test
    public void givenInvalidLastnameFormat_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock lastname entry with non-alphabetical characters
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "192$%", "123 Joke Street", "0412345678");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee with a invalid characters in their phone cannot log in
    @Test
    public void givenInvalidPhoneFormat_whenPostEmployee_thenReturnError() throws Exception
    {
        //Invalid Mock Employee object to mock phone entry with non-numerical characters
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "123 Joke Street", "SHDUH#$%&%");

        //Mock request for invalid employee
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$", is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee that exists can't be updated with inappropriate data
    @Test
    public void givenInvalidEmployee_whenUpdateEmployee_thenReturnError() throws Exception
    {
        //Mock Employee object with null
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "45 Joke Street", "0412345678");
        Employee updMockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", null, "0412345678");

        when(employeeService.updateEmployee(updMockEmployee)).thenReturn(updMockEmployee);

        //Mock request for invalid employee
        mockMvc.perform(put("/api/employee/update/", mockEmployee.getUserName()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(updMockEmployee)))
                .andExpect(jsonPath("$",is(invalidEmp)))
                .andExpect(status().isBadRequest());
    }

    //Tests that an employee that exists can be deleted
    @Test
    public void givenExistingEmp_whenDeleteEmp_thenSuccessfullyDelete() throws Exception
    {
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");
        String delEmployee = "Employee " + mockEmployee.getUserName() + " has been successfully removed";

        given(this.employeeService.deleteEmployee(mockEmployee.getUserName())).willReturn(delEmployee);

        mockMvc.perform(delete("/api/employee/delete/{userName}", "emp").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(jsonPath("$",is(delEmployee)))
                .andExpect(status().isAccepted());
    }

    //Tests that an employee that exists can be retrieved using the GET method
    @Test
    public void givenValidUserName_whenGetEmp_thenReturnEmp() throws Exception
    {
        Employee mockEmployee = new Employee("emp", "password", "admin",
                "Emp", "Loyee", "123 Joke Street", "0412345678");

        given(employeeService.getEmployeeByUserName("emp")).willReturn(mockEmployee);

        mockMvc.perform(get("/api/employee/{userName}", "emp").contentType(MediaType.APPLICATION_JSON)
                .content(objectMap.writeValueAsString(mockEmployee)))
                .andExpect(status().isOk());
    }
}
