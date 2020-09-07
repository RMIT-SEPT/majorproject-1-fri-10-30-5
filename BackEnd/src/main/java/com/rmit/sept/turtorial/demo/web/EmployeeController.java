package com.rmit.sept.turtorial.demo.web;

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
        return new ResponseEntity<String>(employee1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Employee Object", HttpStatus.BAD_REQUEST);
        }
        Employee employee1 = employeeService.updateEmployee(employee);
        return new ResponseEntity<Employee>(employee1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteEmployee(@Valid @PathVariable String userName) {
        String employee1 = employeeService.deleteEmployee(userName);
        return new ResponseEntity<String>(employee1, HttpStatus.CREATED);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> findEmployee(@Valid @PathVariable String userName, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Employee Object", HttpStatus.BAD_REQUEST);
        }
        Employee employee1 = employeeService.getEmployeeByUserName(userName);
        return new ResponseEntity<Employee>(employee1, HttpStatus.CREATED);
    }}
