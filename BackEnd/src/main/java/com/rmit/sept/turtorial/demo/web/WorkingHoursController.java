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

//This class allows for operations to be performed on Working Hours objects.
@CrossOrigin
@RestController
@RequestMapping("/api/workinghours")
public class WorkingHoursController
{
    //Instance of the Working Hours Service
    @Autowired
    private WorkingHoursService wHService;

    //Adds Working Hours using a POST request
    @PostMapping("/add")
    public ResponseEntity<?> addNewWH(@Valid @RequestBody WorkingHours workingHours, BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseEntity<>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
        }
        List<WorkingHours> workingHours1 = wHService.addWH(workingHours);

        if (workingHours1.size() != 0)
        {
            return new ResponseEntity<>(workingHours1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Working Hours Object Could Not Be Created", HttpStatus.BAD_REQUEST);
        }

    }

    //Updates an existing Working Hours object using a PUT request
    @PutMapping("/update")
    public ResponseEntity<?> updateWH(@Valid @RequestBody WorkingHours workingHours, BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseEntity<>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
        }
        WorkingHours workingHours1 = wHService.updateWH(workingHours);

        if (workingHours1 != null)
        {
            return new ResponseEntity<>(workingHours1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Working Hours Object Could Not Be Updated", HttpStatus.CONFLICT);
        }

    }

    //Gets a list of working hours for an employee using a GET request
    @GetMapping("list/{empID}")
    public ResponseEntity<?> findAllByEmployee(@Valid @PathVariable String empID)
    {
        List<WorkingHours> workingHours = wHService.findAllByEmpIDEquals(empID);
        if (workingHours.size() != 0)
        {
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Gets a working hour on a specific day, start time and end time using a GET request
    @GetMapping("list/{empID}/{date}/{startTime}/{endTime}")
    public ResponseEntity<?> findTimesByEIDAndServiceAndDateTime(@Valid @PathVariable String empID,
     @PathVariable String date, @PathVariable int startTime, @PathVariable int endTime, BindingResult result)
    {
        if (result.hasErrors())
        {
            return new ResponseEntity<>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
        }

        WorkingHours workingHours = wHService.getWHByEIDDateTime(empID, date, startTime, endTime);
        if (workingHours != null){
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Gets a list of working hours for an employee on a specific day and start time using a GET request
    @GetMapping("list/{empID}/{date}/{startTime}")
    public ResponseEntity<?> findTimesByEIDAndServiceAndDate(@Valid @PathVariable String empID, @PathVariable String date,
    @PathVariable int startTime)
    {
        List<WorkingHours> workingHours = wHService.getWHByEIDServiceDate(empID, date,startTime);
        if (workingHours.size() != 0)
        {
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Gets an employee's working hours for a specific day using a GET request
    @GetMapping("list/available/{empID}/{date}")
    public ResponseEntity<?> findAvailableTimesByEIDAndServiceAndDate(@Valid @PathVariable String empID, @PathVariable String date){

        List<WorkingHours> workingHours = wHService.availableWHByEIDDate(empID, date);
        if (workingHours != null)
        {
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Gets an employee's unavailable working hours for a specific day using a GET request
    @GetMapping("list/unavailable/{empID}/{date}")
    public ResponseEntity<?> findUnAvailableTimesByEIDAndServiceAndDate(@Valid @PathVariable String empID, @PathVariable String date){

        List<WorkingHours> workingHours = wHService.unavailableWHByEIDDate(empID, date);
        if (workingHours != null)
        {
            return new ResponseEntity<>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }
}