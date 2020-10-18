package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Person;
import com.rmit.sept.turtorial.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//This class allows for operations to be performed on Person objects.
@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class PersonController
{
    //Instance of Person Service
    @Autowired
    private PersonService personService;

    //Adds a Person object using a POST request
    @PostMapping("/add")
    public ResponseEntity<?> createNewPerson(@RequestBody @Valid Person person, BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseEntity<>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        Person person1 = personService.addPerson(person);

        if (person1 != null)
        {
            return new ResponseEntity<>(person1, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Person Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    //Updates an existing Person object using a PUT request
    @PutMapping("/update")
    public ResponseEntity<?> updatePerson(@Valid @RequestBody Person person, BindingResult result)
    {
        if (result.hasErrors())
        {
            System.out.println(result);
            return new ResponseEntity<>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        Person person1 = personService.updatePerson(person);

        if (person1 != null)
        {
            return new ResponseEntity<>(person1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Person Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }

    //Gets an employee using a GET request
    @GetMapping("employee/{userName}")
    public ResponseEntity<?> findEmployee(@Valid @PathVariable String userName)
    {
        Person user = personService.getPersonByUserName(userName);
        if (user != null && user.getUserType().matches("employee"))
        {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Employee Found", HttpStatus.NOT_FOUND);
        }
    }

    //Gets a person using a GET request
    @GetMapping("/{userName}")
    public ResponseEntity<?> findPerson(@Valid @PathVariable String userName)
    {
        Person user = personService.getPersonByUserName(userName);
        if (user != null)
        {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Person Found", HttpStatus.NOT_FOUND);
        }
    }

    //Gets an admin using a GET request
    @GetMapping("admin/{userName}")
    public ResponseEntity<?> findAdmin(@Valid @PathVariable String userName)
    {
        Person user = personService.getPersonByUserName(userName);
        if (user != null && user.getUserType().matches("admin"))
        {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Admin Found", HttpStatus.NOT_FOUND);
        }
    }

    //Gets a customer using a GET request
    @GetMapping("customer/{userName}")
    public ResponseEntity<?> findCustomer(@Valid @PathVariable String userName)
    {
        Person user = personService.getPersonByUserName(userName);
        if (user != null && user.getUserType().matches("customer"))
        {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Customer Found", HttpStatus.NOT_FOUND);
        }
    }

    //Gets a list of all employees using a GET request
    @GetMapping("employee/list")
    public ResponseEntity<?> findAllEmployees()
    {
        List<Person> employees = personService.getEmployees();
        if (employees.size() != 0)
        {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Employees Found", HttpStatus.NOT_FOUND);
        }
    }

    //Gets a list of all customers using a GET request
    @GetMapping("customer/list")
    public ResponseEntity<?> findAllCustomers()
    {
        List<Person> customers = personService.getCustomers();
        if (customers.size() != 0)
        {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Customers Found", HttpStatus.NOT_FOUND);
        }
    }
}