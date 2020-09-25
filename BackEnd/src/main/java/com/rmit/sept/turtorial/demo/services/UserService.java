package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.UserRepository;
import com.rmit.sept.turtorial.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
}
