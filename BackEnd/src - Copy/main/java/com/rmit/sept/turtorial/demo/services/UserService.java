package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.UserRepository;
import com.rmit.sept.turtorial.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User saveOrUpdatePerson(User user) {

        //logic
        return userRepository.save(user);
    }
}
