package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Customer;
import com.rmit.sept.turtorial.demo.model.Employee;
import com.rmit.sept.turtorial.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
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

        if (customer1 != null)
        {
            return new ResponseEntity<String>(customer1, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>("Customer Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Customer Object", HttpStatus.BAD_REQUEST);
        }
        Customer customer1 = customerService.updateCustomer(customer);
        if (customer1 != null)
        {
            return new ResponseEntity<Customer>(customer1, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>("Customer Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteCustomer(@Valid @PathVariable String userName)
    {
        String customer1 = customerService.deleteCustomer(userName);

        if (customer1 != null){
            return new ResponseEntity<String>(customer1, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("No Customer Object", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> findCustomer(@Valid @PathVariable String userName) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Customer Object", HttpStatus.BAD_REQUEST);
//        }
        Customer customer1 = customerService.getCustomerByUserName(userName);
        if (customer1 != null){
            return new ResponseEntity<Customer>(customer1, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("No Customer Object", HttpStatus.NOT_FOUND);
        }
    }
}

