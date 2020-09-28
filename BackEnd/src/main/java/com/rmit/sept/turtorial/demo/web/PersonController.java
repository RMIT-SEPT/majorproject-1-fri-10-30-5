package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Booking;
import com.rmit.sept.turtorial.demo.model.Person;
import com.rmit.sept.turtorial.demo.services.BookingService;
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

    @Autowired
    private BookingService bookingService;

    @PostMapping("/employee/add")
    public ResponseEntity<?> createNewEmployee(@RequestBody @Valid Person employee, BindingResult result) {
        if (result.hasErrors() || employee.getEmplChecked() != true) {
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        Person person = personService.addPerson(employee);
        if (person != null) {
            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Person Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/admin/add")
    public ResponseEntity<?> createNewAdmin(@RequestBody @Valid Person admin, BindingResult result) {
        if (result.hasErrors() || admin.getAdminChecked() != true) {
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        Person person = personService.addPerson(admin);
        if (person != null) {
            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Person Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/customer/add")
    public ResponseEntity<?> createNewCustomer(@RequestBody @Valid Person customer, BindingResult result) {
        if (result.hasErrors() || customer.getCustChecked() != true) {
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        Person person = personService.addPerson(customer);
        if (person != null) {
            return new ResponseEntity<Person>(person, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Person Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/employee/update")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Person employee, BindingResult result) {
        if (result.hasErrors() || employee.getEmplChecked() != true) {
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        Person employee1 = personService.updatePerson(employee);
        if (employee1 != null) {
            return new ResponseEntity<Person>(employee1, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Person Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/admin/update")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody Person admin, BindingResult result) {
        if (result.hasErrors() || admin.getAdminChecked() != true) {
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        Person admin1 = personService.updatePerson(admin);
        if (admin1 != null) {
            return new ResponseEntity<Person>(admin1, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Person Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/customer/update")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Person customer, BindingResult result) {
        if (result.hasErrors() || customer.getCustChecked() != true) {
            return new ResponseEntity<String>("Invalid Person Object", HttpStatus.BAD_REQUEST);
        }
        Person customer1 = personService.updatePerson(customer);
        if (customer1 != null) {
            return new ResponseEntity<Person>(customer1, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Person Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("employee/delete/{userName}")
    public ResponseEntity<?> deleteEmployee(@Valid @PathVariable String userName) {
        String person1 = personService.deletePerson(userName);
        Boolean isEmpl = personService.getPersonByUserName(userName).getEmplChecked();
        if (person1 != null && isEmpl == true) {
            return new ResponseEntity<String>(person1, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>("No Person Object", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("admin/delete/{userName}")
    public ResponseEntity<?> deleteAdmin(@Valid @PathVariable String userName) {
        String person1 = personService.deletePerson(userName);
        Boolean isAdmin = personService.getPersonByUserName(userName).getAdminChecked();
        if (person1 != null && isAdmin) {
            return new ResponseEntity<String>(person1, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>("No Person Object", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("customer/delete/{userName}")
    public ResponseEntity<?> deleteCustomer(@Valid @PathVariable String userName) {
        String person1 = personService.deletePerson(userName);
        Boolean isCust = personService.getPersonByUserName(userName).getCustChecked();
        if (person1 != null && isCust) {
            return new ResponseEntity<String>(person1, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>("No Person Object", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("employee/{userName}")
    public ResponseEntity<?> findEmployee(@Valid @PathVariable String userName) {

        Person user = personService.getPersonByUserName(userName);
        if (user != null && user.getEmplChecked()) {
            return new ResponseEntity<Person>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No Person Object", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("admin/{userName}")
    public ResponseEntity<?> findAdmin(@Valid @PathVariable String userName) {

        Person user = personService.getPersonByUserName(userName);
        if (user != null && user.getAdminChecked()) {
            return new ResponseEntity<Person>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No Person Object", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("customer/{userName}")
    public ResponseEntity<?> findCustomer(@Valid @PathVariable String userName) {

        Person user = personService.getPersonByUserName(userName);
        if (user != null && user.getCustChecked()) {
            return new ResponseEntity<Person>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No Person Object", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("admin/past-Bookings")
    public ResponseEntity<?> getPastBookings()
    {
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookings(true);
        if (bookings.size() != 0){
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("admin/upcoming-Bookings")
    public ResponseEntity<?> getUpcomingBookings()
    {
        List<Booking> bookings = bookingService.findAllPastOrUpcomingBookings(false);
        if (bookings.size() != 0){
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Booking Objects", HttpStatus.NOT_FOUND);
        }
    }

}

