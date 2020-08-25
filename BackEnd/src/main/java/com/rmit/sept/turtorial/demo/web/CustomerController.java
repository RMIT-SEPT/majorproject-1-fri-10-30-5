package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Customer;
import com.rmit.sept.turtorial.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> createNewCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Customer Object", HttpStatus.BAD_REQUEST);
        }
        String customer1 = customerService.addCustomer(customer);
        return new ResponseEntity<String>(customer1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Customer Object", HttpStatus.BAD_REQUEST);
        }
        Customer customer1 = customerService.updateCustomer(customer);
        return new ResponseEntity<Customer>(customer1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteCustomer(@Valid @PathVariable String userName) {
        String customer1 = customerService.deleteCustomer(userName);
        return new ResponseEntity<String>(customer1, HttpStatus.CREATED);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> findCustomer(@Valid @PathVariable String userName, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Customer Object", HttpStatus.BAD_REQUEST);
        }
        Customer customer1 = customerService.getCustomerByUserName(userName);
        return new ResponseEntity<Customer>(customer1, HttpStatus.CREATED);
    }
}

