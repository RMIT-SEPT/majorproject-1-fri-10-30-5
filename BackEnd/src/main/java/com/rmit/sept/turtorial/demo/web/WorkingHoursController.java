package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.WorkingHours;
import com.rmit.sept.turtorial.demo.services.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/workinghours")
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

    @GetMapping("list/{empID}")
    public ResponseEntity<?> findAllByEmployee(@Valid @PathVariable String empID) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
//        }
        List<WorkingHours> workingHours = wHService.findAllByEmpIDEquals(empID);
        return new ResponseEntity<List<WorkingHours>>(workingHours, HttpStatus.CREATED);
    }

//    @GetMapping("/{empID}")
//    public ResponseEntity<?> findEmployee(@Valid @PathVariable String empID) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
//        }
//        WorkingHours workingHours = wHService.getWHByEmpID(empID);
//        return new ResponseEntity<WorkingHours>(workingHours, HttpStatus.CREATED);
//    }

    @GetMapping("list/{empID}/{service}")
    public ResponseEntity<?> findTimesByEIDAndService(@Valid @PathVariable String empID, @PathVariable String service) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Service Object", HttpStatus.BAD_REQUEST);
//        }
        List<WorkingHours> workingHours = wHService.getWHByEmpIDandService(empID, service);
        return new ResponseEntity<List<WorkingHours>>(workingHours, HttpStatus.CREATED);
    }

    @GetMapping("list/{empID}/{service}/{date}/{startTime}/{endTime}")
    public ResponseEntity<?> findTimesByEIDAndServiceAndDateTime(@Valid @PathVariable String empID, @PathVariable String service,
     @PathVariable String date, @PathVariable int startTime, @PathVariable int endTime){
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Service Object", HttpStatus.BAD_REQUEST);
//        }
        List<WorkingHours> workingHours = wHService.getWHByEIDServiceDateTime(empID, service, date, startTime, endTime);
        return new ResponseEntity<List<WorkingHours>>(workingHours, HttpStatus.CREATED);
    }

    @GetMapping("list/{empID}/{service}/{date}")
    public ResponseEntity<?> findTimesByEIDAndServiceAndDateTime(@Valid @PathVariable String empID, @PathVariable String service, @PathVariable String date){
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Service Object", HttpStatus.BAD_REQUEST);
//        }
        List<WorkingHours> workingHours = wHService.getWHByEIDServiceDate(empID, service, date);
        return new ResponseEntity<List<WorkingHours>>(workingHours, HttpStatus.CREATED);
    }
}
