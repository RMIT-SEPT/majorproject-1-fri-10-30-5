package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.AdminRepository;
import com.rmit.sept.turtorial.demo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    //post services
    public String addAdmin(Admin admin) {
        String message;
        Admin admin1 = adminRepository.findById(admin.getUserName()).orElse(null);
        if (admin1 == null){
            adminRepository.save(admin);
            message = admin.getUserName() + " added successfully";
        }else{
            message = admin.getUserName() + " already exists";
        }
        return message;
    }

    //get services
    public Admin getAdminByUserName(String userName) {

        //logic
        return adminRepository.findById(userName).orElse(null);
    }

    //put services
    public Admin updateAdmin(Admin admin) {
        Admin admin1 = adminRepository.findById(admin.getUserName()).orElse(null);
        if (admin1 != null){
            admin1.setName(admin.getName());
            admin1.setPassword(admin.getPassword());
            admin1.setPhone(admin.getPhone());
            admin1.setAddress(admin.getAddress());
            admin1.setUpdated_At(new Date());
        }
        return adminRepository.save(admin1);
    }

    //delete services
    public String deleteAdmin(String userName){
        adminRepository.deleteById(userName);
        return "Admin " + userName + " has been successfully removed";
    }
}
