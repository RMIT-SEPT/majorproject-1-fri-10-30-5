package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.UserRepository;
import com.rmit.sept.turtorial.demo.exceptions.UsernameAlreadyExistsException;
import com.rmit.sept.turtorial.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //post services
    public String addUser(User user) {
        String message;
//        User user1 = userRepository.findById(user.getUserName()).orElse(null);
        User user1 = userRepository.findByUserName(user.getUserName());
        if (user1 == null){
            userRepository.save(user);
            message = user.getUserName() + " added successfully";
        }else{
            message = user.getUserName() + " already exists";
        }
        return message;
    }

    //get services
    public User getUserByUserName(String userName) {

        //logic
        return userRepository.findByUserName(userName);
    }

    //put services
    public User updateUser(User user) {
        User user1 = userRepository.findByUserName(user.getUserName());
        if (user1 != null){
            user1.setPassword(user.getPassword());
            user1.setUserType(user.getUserType());
            user1.setUpdated_At(new Date());
        }
        return userRepository.save(user1);
    }

    //delete services
    public String deleteUser(String userName){
        userRepository.deleteByUserName(userName);
        return "User " + userName + " has been successfully removed";
    }

    public User saveUser (User newUser){

      /*  newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        //Username has to be unique (exception)
        // Make sure that password and confirmPassword match
        // We don't persist or show the confirmPassword
        return userRepository.save(newUser);
       */
        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUserName(newUser.getUsername());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }
}
