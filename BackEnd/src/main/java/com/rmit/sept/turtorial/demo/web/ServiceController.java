package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.EmpService;
import com.rmit.sept.turtorial.demo.services.EmpServiceService;
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
public class EmpServiceController
{
    @Autowired
    EmpServiceService empServiceService;

    @PostMapping("/add")
    public ResponseEntity<?> createService(@Valid @RequestBody EmpService service, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Service Object", HttpStatus.BAD_REQUEST);
        }
        EmpService service1 = empServiceService.addService(service);
        if (service1 != null) {
            return new ResponseEntity<EmpService>(service1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("Service Object Could Not Be Created", HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateService(@Valid @RequestBody EmpService service, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Service Object", HttpStatus.BAD_REQUEST);
        }
        EmpService service1 = empServiceService.updateService(service);
        if (service1 != null){
            return new ResponseEntity<EmpService>(service1, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Service Object Could Not Be Updated", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAllServices()
    {
        List<EmpService> services = empServiceService.getService();
        if (services.size() != 0){
            return new ResponseEntity<List<EmpService>>(services, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Service Objects", HttpStatus.NOT_FOUND);
        }
    }
}
