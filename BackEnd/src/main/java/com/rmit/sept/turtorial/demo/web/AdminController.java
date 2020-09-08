package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Admin;
import com.rmit.sept.turtorial.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<?> createNewAdmin(@Valid @RequestBody Admin admin, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Admin Object", HttpStatus.BAD_REQUEST);
        }
        String admin1 = adminService.addAdmin(admin);
        return new ResponseEntity<String>(admin1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody Admin admin, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid Admin Object", HttpStatus.BAD_REQUEST);
        }
        Admin admin1 = adminService.updateAdmin(admin);
        return new ResponseEntity<Admin>(admin1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteAdmin(@Valid @PathVariable String userName) {
        String admin1 = adminService.deleteAdmin(userName);
        return new ResponseEntity<String>(admin1, HttpStatus.CREATED);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> findAdmin(@Valid @PathVariable String userName) {
//        if (result.hasErrors()){
//            return new ResponseEntity<String>("Invalid Admin Object", HttpStatus.BAD_REQUEST);
//        }
        Admin admin1 = adminService.getAdminByUserName(userName);
        return new ResponseEntity<Admin>(admin1, HttpStatus.CREATED);
    }
}

