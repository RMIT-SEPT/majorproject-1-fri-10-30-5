package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.AssignedService;
import com.rmit.sept.turtorial.demo.services.AssignedServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/assignService")
public class AssignedServiceController
{
    @Autowired
    AssignedServiceService assignedServiceService;

    @PostMapping("/add")
    public ResponseEntity<?> addAssignedService(@Valid @RequestBody AssignedService assignedService, BindingResult result)
    {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Assigned Service Object", HttpStatus.BAD_REQUEST);
        }
        AssignedService assignedService1 = assignedServiceService.addAssignedService(assignedService);
        if (assignedService1 != null) {
            return new ResponseEntity<AssignedService>(assignedService1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("Assigned Service Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllAssignedServices()
    {
        List<AssignedService> assignedServices = assignedServiceService.getAllAssignedServices();
        if (assignedServices.size() != 0){
            return new ResponseEntity<List<AssignedService>>(assignedServices, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Assigned Service Objects", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list/{userName}")
    public ResponseEntity<?> getAssignedServiceByEmployee(@Valid@PathVariable String userName)
    {
        List<AssignedService> assignedServices = assignedServiceService.getAssignedServicesByServiceId(userName);
        if (assignedServices.size() != 0){
            return new ResponseEntity<List<AssignedService>>(assignedServices, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Assigned Service Objects", HttpStatus.NOT_FOUND);
        }
    }
}
