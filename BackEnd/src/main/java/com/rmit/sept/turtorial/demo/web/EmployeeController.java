package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Booking;
import com.rmit.sept.turtorial.demo.model.Employee;
import com.rmit.sept.turtorial.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<?> createNewEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Employee Object", HttpStatus.BAD_REQUEST);
        }

        String employee1 = employeeService.addEmployee(employee);

        if (employee1 != null)
        {
            return new ResponseEntity<String>(employee1, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>("Employee Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Employee Object", HttpStatus.BAD_REQUEST);
        }

        Employee employee1 = employeeService.updateEmployee(employee);

        if (employee1 != null)
        {
            return new ResponseEntity<Employee>(employee1, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>("Employee Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteEmployee(@Valid @PathVariable String userName)
    {
        String employee1 = employeeService.deleteEmployee(userName);

        if (employee1 != null){
            return new ResponseEntity<String>(employee1, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("No Employee Object", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> findEmployee(@Valid @PathVariable String userName, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Employee Object", HttpStatus.BAD_REQUEST);
        }

        Employee employee1 = employeeService.getEmployeeByUserName(userName);

        if (employee1 != null){
            return new ResponseEntity<Employee>(employee1, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("No Employee Object", HttpStatus.NOT_FOUND);
        }
    }}
