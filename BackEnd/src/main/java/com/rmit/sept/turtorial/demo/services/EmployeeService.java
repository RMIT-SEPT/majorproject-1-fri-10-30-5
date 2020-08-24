package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.EmployeeRepository;
import com.rmit.sept.turtorial.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee saveOrUpdateEmployee(Employee employee) {

        //logic
        return employeeRepository.save(employee);
    }
}
