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

@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public ResponseEntity<?> createNewPerson(@RequestBody @Valid Person person, BindingResult result) {
//        if (result.hasErrors() || person.getEmployeeCheck() != true ) {
//            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
//        }
        Person person1 = personService.addPerson(person);
        if (person1 != null) {
            return new ResponseEntity<Person>(person1, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Person Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

//    @PostMapping("/admin/add")
//    public ResponseEntity<?> createNewAdmin(@RequestBody @Valid Person admin, BindingResult result) {
//        if (result.hasErrors() || admin.getAdminCheck() != true) {
//            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
//        }
//        Person person = personService.addPerson(admin);
//        if (person != null) {
//            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<String>("Person Object Could Not Be Created", HttpStatus.CONFLICT);
//        }
//    }
//
//    @PostMapping("/customer/add")
//    public ResponseEntity<?> createNewCustomer(@RequestBody @Valid Person customer, BindingResult result) {
//        if (result.hasErrors() || customer.getCustomerCheck() != true) {
//            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
//        }
//        Person person = personService.addPerson(customer);
//        if (person != null) {
//            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<String>("Person Object Could Not Be Created", HttpStatus.CONFLICT);
//        }
//    }

    //
    @PutMapping("/update")
    public ResponseEntity<?> updatePerson(@Valid @RequestBody Person person, BindingResult result) {
//        if (result.hasErrors() || person.getEmployeeCheck() != true) {
//            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
//        }
        Person person1 = personService.updatePerson(person);
        if (person1 != null) {
            return new ResponseEntity<Person>(person1, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Person Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }

//    @PutMapping("/admin/update")
//    public ResponseEntity<?> updateAdmin(@Valid @RequestBody Person admin, BindingResult result) {
//        if (result.hasErrors() || admin.getAdminCheck() != true) {
//            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
//        }
//        Person admin1 = personService.updatePerson(admin);
//        if (admin1 != null) {
//            return new ResponseEntity<Person>(admin1, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<String>("Person Object Could Not Be Updated", HttpStatus.CONFLICT);
//        }
//    }
//
//
//    @PutMapping("/employee/update")
//    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Person customer, BindingResult result) {
//        if (result.hasErrors() || customer.getCustomerCheck() != true) {
//            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
//        }
//        Person customer1 = personService.updatePerson(customer);
//        if (customer1 != null) {
//            return new ResponseEntity<Person>(customer1, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<String>("Person Object Could Not Be Updated", HttpStatus.CONFLICT);
//        }
//    }

//    @DeleteMapping("/delete/{userName}")
//    public ResponseEntity<?> deleteEmployee(@Valid @PathVariable String userName) {
//        String person1 = personService.deletePerson(userName);
//        Boolean isEmpl = personService.getPersonByUserName(userName).getEmployeeCheck();
//        if (person1 != null && isEmpl) {
//            return new ResponseEntity<String>(person1, HttpStatus.ACCEPTED);
//        } else {
//            return new ResponseEntity<String>("No Person Object", HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("admin/delete/{userName}")
//    public ResponseEntity<?> deleteAdmin(@Valid @PathVariable String userName) {
//        String person1 = personService.deletePerson(userName);
//        Boolean isAdmin = personService.getPersonByUserName(userName).getAdminCheck();
//        if (person1 != null && isAdmin) {
//            return new ResponseEntity<String>(person1, HttpStatus.ACCEPTED);
//        } else {
//            return new ResponseEntity<String>("No Person Object", HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("customer/delete/{userName}")
//    public ResponseEntity<?> deleteCustomer(@Valid @PathVariable String userName) {
//        String person1 = personService.deletePerson(userName);
//        Boolean isCust = personService.getPersonByUserName(userName).getCustomerCheck();
//        if (person1 != null && isCust) {
//            return new ResponseEntity<String>(person1, HttpStatus.ACCEPTED);
//        } else {
//            return new ResponseEntity<String>("No Person Object", HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("employee/{userName}")
    public ResponseEntity<?> findEmployee(@Valid @PathVariable String userName) {

        Person user = personService.getPersonByUserName(userName);
        if (user != null && user.getEmployeeCheck()) {
            return new ResponseEntity<Person>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No Employee Found", HttpStatus.NOT_FOUND);
        }
    }

    //Not used
    @GetMapping("admin/{userName}")
    public ResponseEntity<?> findAdmin(@Valid @PathVariable String userName) {

        Person user = personService.getPersonByUserName(userName);
        if (user != null && user.getAdminCheck()) {
            return new ResponseEntity<Person>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No Admin Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("customer/{userName}")
    public ResponseEntity<?> findCustomer(@Valid @PathVariable String userName) {

        Person user = personService.getPersonByUserName(userName);
        if (user != null && user.getCustomerCheck()) {
            return new ResponseEntity<Person>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No Customer Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("employee/list")
    public ResponseEntity<?> findAllEmployees() {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
//        }
        List<Person> employees = personService.getEmployees();
        if (employees.size() != 0){
            return new ResponseEntity<List<Person>>(employees, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Person Objects", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("customer/list")
    public ResponseEntity<?> findAllCustomers() {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
//        }
        List<Person> customers = personService.getCustomers();
        if (customers.size() != 0){
            return new ResponseEntity<List<Person>>(customers, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Person Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Fix update logic
}

