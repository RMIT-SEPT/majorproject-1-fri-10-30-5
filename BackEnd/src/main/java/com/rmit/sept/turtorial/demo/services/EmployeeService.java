package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.EmployeeRepository;
import com.rmit.sept.turtorial.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    //post services
    public String addEmployee(Employee employee) {
        String message;
        Employee employee1 = employeeRepository.findById(employee.getUserName()).orElse(null);
        if (employee1 == null) {
            employeeRepository.save(employee);
            message = employee.getUserName() + " added successfully";
        } else {
            message = employee.getUserName() + " already exists";
        }
        return message;
    }

    //get services
    public Employee getEmployeeByUserName(String userName) {

        //logic
        return employeeRepository.findById(userName).orElse(null);
    }

    //put services
    public Employee updateEmployee(Employee employee) {
        Employee employee1 = employeeRepository.findById(employee.getUserName()).orElse(null);

        if (employee1 != null) {
            employee1.setName(employee.getName());
            employee1.setPassword(employee.getPassword());
            employee1.setPhone(employee.getPhone());
            employee1.setAdmin(employee.getAdmin());
            employee1.setAddress(employee.getAddress());
            employee1.setUpdated_At(new Date());
        }
        return employeeRepository.save(employee1);
    }

    //delete services
    public String deleteEmployee(String userName) {
        employeeRepository.deleteById(userName);
        return "Employee " + userName + " has been successfully removed";
    }
}
