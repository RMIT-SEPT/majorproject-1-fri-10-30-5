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

@CrossOrigin
@RestController
@RequestMapping("/api/service")
public class ServiceController
{
    @Autowired
    ServiceService serviceService;

    @PostMapping("/add")
    public ResponseEntity<?> createService(@Valid @RequestBody Service service, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Service Object", HttpStatus.BAD_REQUEST);
        }
        Service service1 = serviceService.addService(service);
        if (service1 != null) {
            return new ResponseEntity<Service>(service1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("Service Object Could Not Be Created", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateService(@Valid @RequestBody Service service, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Service Object", HttpStatus.BAD_REQUEST);
        }
        Service service1 = serviceService.updateService(service);
        if (service1 != null){
            return new ResponseEntity<Service>(service1, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Service Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAllServices()
    {
        List<Service> services = serviceService.getService();
        if (services.size() != 0){
            return new ResponseEntity<List<Service>>(services, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Service Objects", HttpStatus.NOT_FOUND);
        }
    }
}
