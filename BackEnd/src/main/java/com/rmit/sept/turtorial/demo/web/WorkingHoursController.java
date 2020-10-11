package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.WorkingHours;
import com.rmit.sept.turtorial.demo.services.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
    This controller allows for operations to be performed on Working Hours objects.
    Operations include: Adding a working hours object, updating a working hours object,
    getting a working hours object, getting a list of working hours by employee and
    getting a working hours object for an employee on a specified day
 */
@CrossOrigin
@RestController
@RequestMapping("/api/workinghours")
public class WorkingHoursController
{
    //An instance of the Working Hours Service
    @Autowired
    private WorkingHoursService wHService;

    //This method adds a Working Hours object
    @PostMapping("/add")
    public ResponseEntity<?> addNewWH(@Valid @RequestBody WorkingHours workingHours, BindingResult result)
    {
        if (result.hasErrors()){
            return new ResponseEntity<>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
        }
        WorkingHours workingHours1 = wHService.addWH(workingHours);
        if (workingHours1 != null){
            return new ResponseEntity<>(workingHours1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Working Hours Object Could Not Be Created", HttpStatus.CONFLICT);
        }

    }

    //This method updates an existing Working Hours object
    @PutMapping("/update")
    public ResponseEntity<?> updateWH(@Valid @RequestBody WorkingHours workingHours, BindingResult result)
    {
        if (result.hasErrors()){
            return new ResponseEntity<>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
        }
        WorkingHours workingHours1 = wHService.updateWH(workingHours);
        if (workingHours1 != null){
            return new ResponseEntity<>(workingHours1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Working Hours Object Could Not Be Updated", HttpStatus.CONFLICT);
        }

    }

    //This method gets a list of working hours for an employee
    @GetMapping("list/{empID}")
    public ResponseEntity<?> findAllByEmployee(@Valid @PathVariable String empID)
    {

        List<WorkingHours> workingHours = wHService.findAllByEmpIDEquals(empID);
        if (workingHours.size() != 0){
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }

    //This method gets the working hours for an employee on a specific day
    @GetMapping("list/{empID}/{date}/{startTime}/{endTime}")
    public ResponseEntity<?> findTimesByEIDAndServiceAndDateTime(@Valid @PathVariable String empID,
     @PathVariable String date, @PathVariable int startTime, @PathVariable int endTime)
    {

        List<WorkingHours> workingHours = wHService.getWHByEIDDateTime(empID, date, startTime, endTime);
        if (workingHours.size() != 0){
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }

    }

    //This method gets an employee's working hours for a specific day
    @GetMapping("list/{empID}/{date}")
    public ResponseEntity<?> findTimesByEIDAndServiceAndDate(@Valid @PathVariable String empID, @PathVariable String date){

        WorkingHours workingHours = wHService.getWHByEIDServiceDate(empID, date);
        if (workingHours != null){
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }

    //This method gets an employee's working hours for a specific day
    @GetMapping("list/available/{empID}/{date}")
    public ResponseEntity<?> findAvailableTimesByEIDAndServiceAndDate(@Valid @PathVariable String empID, @PathVariable String date){

        List<WorkingHours> workingHours = wHService.availableWHByEIDDate(empID, date);
        if (workingHours != null){
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }

    //This method gets an employee's working hours for a specific day
    @GetMapping("list/unavailable/{empID}/{date}")
    public ResponseEntity<?> findUnAvailableTimesByEIDAndServiceAndDate(@Valid @PathVariable String empID, @PathVariable String date){

        List<WorkingHours> workingHours = wHService.unavailableWHByEIDDate(empID, date);
        if (workingHours != null){
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }


}
