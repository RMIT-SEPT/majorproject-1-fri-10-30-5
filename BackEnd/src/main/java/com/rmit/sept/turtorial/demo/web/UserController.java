package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.User;
import com.rmit.sept.turtorial.demo.services.UserService;
//import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid User Object", HttpStatus.BAD_REQUEST);
        }
        String user1 = userService.addUser(user);
        return new ResponseEntity<String>(user1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid User Object", HttpStatus.BAD_REQUEST);
        }
        User user1 = userService.updateUser(user);
        return new ResponseEntity<User>(user1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteCustomer(@Valid @PathVariable String userName) {
        String customer1 = userService.deleteUser(userName);
        return new ResponseEntity<String>(customer1, HttpStatus.CREATED);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> findCustomer(@Valid @PathVariable String userName, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<String>("Invalid User Object", HttpStatus.BAD_REQUEST);
        }
        User user1 = userService.getUserByUserName(userName);
        return new ResponseEntity<User>(user1, HttpStatus.CREATED);
    }

//    public static boolean authenticate(String username, String password) {
//        if (username == null || password == null) {
//            return false;
//        }
//        User user = userService.getUserByUsername(username);
//        if (user == null) {
//            return false;
//        }
//        String hashedPassword = BCrypt.hashpw(password, SALT);
//        return hashedPassword.equals(user.getPassword());
//    }
//
//    public static String generatePassword(String password) {
//        String newHashedPassword = BCrypt.hashpw(password, SALT);
//        return newHashedPassword;
//
//    }
}