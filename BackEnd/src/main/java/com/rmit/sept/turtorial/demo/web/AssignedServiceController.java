package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.AssignedService;
import com.rmit.sept.turtorial.demo.model.Person;
import com.rmit.sept.turtorial.demo.services.AssignedServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// This class allows for operations to be performed on Assigned Service objects.
@CrossOrigin
@RestController
@RequestMapping("/api/assignService")
public class AssignedServiceController
{
    //Instance of Assigned Service Service
    @Autowired
    AssignedServiceService assignedServiceService;

    //Adds an assigned service using a POST request
    @PostMapping("/add")
    public ResponseEntity<?> addAssignedService(@Valid @RequestBody AssignedService assignedService, BindingResult result)
    {
        if (result.hasErrors()){
            return new ResponseEntity<>("Invalid Assigned Service Object", HttpStatus.BAD_REQUEST);
        }
        AssignedService assignedService1 = assignedServiceService.addAssignedService(assignedService);

        if (assignedService1 != null)
        {
            return new ResponseEntity<>(assignedService1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Assigned Service Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    //Returns a list of all assigned services using a GET request
    @GetMapping("/list")
    public ResponseEntity<?> getAllAssignedServices()
    {
        List<AssignedService> assignedServices = assignedServiceService.getAllAssignedServices();
        if (assignedServices.size() != 0)
        {
            return new ResponseEntity<>(assignedServices, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Assigned Service Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Returns a list of assigned services for an employee using a GET request
    @GetMapping("/service-list/{userName}")
    public ResponseEntity<?> getAssignedServiceByEmployee(@Valid@PathVariable String userName)
    {
        List<AssignedService> assignedServices = assignedServiceService.getAssignedServicesByUserName(userName);
        if (assignedServices.size() != 0){
            return new ResponseEntity<>(assignedServices, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Assigned Service Objects", HttpStatus.NOT_FOUND);
        }
    }

    //Returns a list of employees for an assigned service using a GET request
    @GetMapping("/employee-list/{serviceId}")
    public ResponseEntity<?> getEmployeesByAssignedService(@Valid@PathVariable long serviceId)
    {
        List<Person> employees = assignedServiceService.getAllEmployeesByAssignedService(serviceId);
        if (employees.size() != 0){
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Employee Objects", HttpStatus.NOT_FOUND);
        }
    }
}
