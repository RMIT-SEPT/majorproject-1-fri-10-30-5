package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.WorkingHours;
import com.rmit.sept.turtorial.demo.services.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class WorkingHoursController
{
    @Autowired
    private WorkingHoursService wHService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewWH(@Valid @RequestBody WorkingHours workingHours, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Working_Hours Object", HttpStatus.BAD_REQUEST);
        }
        WorkingHours workingHours1 = wHService.addWH(workingHours);
        return new ResponseEntity<WorkingHours>(workingHours1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateWH(@Valid @RequestBody WorkingHours workingHours, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Working_Hours Object", HttpStatus.BAD_REQUEST);
        }
        WorkingHours workingHours1 = wHService.updateWH(workingHours);
        return new ResponseEntity<WorkingHours>(workingHours1, HttpStatus.CREATED);
    }

    @GetMapping("/{empID}")
    public ResponseEntity<?> findEmployee(@Valid @PathVariable String empID, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
        }
        WorkingHours workingHours = wHService.getWHByEmpID(empID);
        return new ResponseEntity<WorkingHours>(workingHours, HttpStatus.CREATED);
    }
}
