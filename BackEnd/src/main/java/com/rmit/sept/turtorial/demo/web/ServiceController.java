package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Service;
import com.rmit.sept.turtorial.demo.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
    This controller allows for operations to be performed on Service objects.
    Operations include: Adding a service object, updating a service object and
    getting a list of all service objects
 */
@CrossOrigin
@RestController
@RequestMapping("/api/service")
public class ServiceController
{
    //An instance of Service Service
    @Autowired
    ServiceService serviceService;

    //This method adds a new Service object
    @PostMapping("/add")
    public ResponseEntity<?> createService(@Valid @RequestBody Service service, BindingResult result) {
        if (result.hasErrors())
        {
            return new ResponseEntity<>("Invalid Service Object", HttpStatus.BAD_REQUEST);
        }
        Service service1 = serviceService.addService(service);
        if (service1 != null) {
            return new ResponseEntity<>(service1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Service Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    //This method updates a service object
    @PutMapping("/update")
    public ResponseEntity<?> updateService(@Valid @RequestBody Service service, BindingResult result) {
        if (result.hasErrors())
        {
            return new ResponseEntity<>("Invalid Service Object", HttpStatus.BAD_REQUEST);
        }
        Service service1 = serviceService.updateService(service);
        if (service1 != null){
            return new ResponseEntity<>(service1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Service Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }

    //This method gets a list of all services
    @GetMapping("/list")
    public ResponseEntity<?> findAllServices()
    {
        List<Service> services = serviceService.getAllServices();
        if (services.size() != 0)
        {
            return new ResponseEntity<>(services, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Service Objects", HttpStatus.NOT_FOUND);
        }
    }
}