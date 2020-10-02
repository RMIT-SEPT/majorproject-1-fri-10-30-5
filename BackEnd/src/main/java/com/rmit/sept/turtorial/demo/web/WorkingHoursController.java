package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Booking;
import com.rmit.sept.turtorial.demo.model.WorkingHours;
import com.rmit.sept.turtorial.demo.services.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/workinghours")
public class WorkingHoursController
{
    @Autowired
    private WorkingHoursService wHService;

    //Update this
    @PostMapping("/add")
    public ResponseEntity<?> addNewWH(@Valid @RequestBody WorkingHours workingHours, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Working_Hours Object", HttpStatus.BAD_REQUEST);
        }
        WorkingHours workingHours1 = wHService.addWH(workingHours);
        if (workingHours1 != null){
            return new ResponseEntity<WorkingHours>(workingHours1, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("Working Hours Object Could Not Be Created", HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateWH(@Valid @RequestBody WorkingHours workingHours, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Working_Hours Object", HttpStatus.BAD_REQUEST);
        }
        WorkingHours workingHours1 = wHService.updateWH(workingHours);
        if (workingHours1 != null){
            return new ResponseEntity<WorkingHours>(workingHours1, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Working Hours Object Could Not Be Updated", HttpStatus.CONFLICT);
        }

    }

    @GetMapping("list/{empID}")
    public ResponseEntity<?> findAllByEmployee(@Valid @PathVariable String empID) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Working Hours Object", HttpStatus.BAD_REQUEST);
//        }
        List<WorkingHours> workingHours = wHService.findAllByEmpIDEquals(empID);
        if (workingHours.size() != 0){
            return new ResponseEntity<List<WorkingHours>>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
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
        if (workingHours.size() != 0){
            return new ResponseEntity<List<WorkingHours>>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("list/{empID}/{service}/{date}/{startTime}/{endTime}")
    public ResponseEntity<?> findTimesByEIDAndServiceAndDateTime(@Valid @PathVariable String empID, @PathVariable String service,
     @PathVariable String date, @PathVariable int startTime, @PathVariable int endTime){
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Service Object", HttpStatus.BAD_REQUEST);
//        }
        List<WorkingHours> workingHours = wHService.getWHByEIDServiceDateTime(empID, service, date, startTime, endTime);
        if (workingHours.size() != 0){
            return new ResponseEntity<List<WorkingHours>>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("list/{empID}/{service}/{date}")
    public ResponseEntity<?> findTimesByEIDAndServiceAndDate(@Valid @PathVariable String empID, @PathVariable String service, @PathVariable String date){
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Service Object", HttpStatus.BAD_REQUEST);
//        }
        List<WorkingHours> workingHours = wHService.getWHByEIDServiceDate(empID, service, date);
        if (workingHours.size() != 0){
            return new ResponseEntity<List<WorkingHours>>(workingHours, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No Working Hours Objects", HttpStatus.NOT_FOUND);
        }
    }
}
