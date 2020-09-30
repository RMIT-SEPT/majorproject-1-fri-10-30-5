package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.AssignedServiceRepository;
import com.rmit.sept.turtorial.demo.model.AssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignedServiceService
{
    @Autowired
    AssignedServiceRepository assignedServiceRepository;

    //Post assigned service
    public AssignedService addAssignedService(AssignedService assignedService)
    {
        return assignedServiceRepository.save(assignedService);
    }

    public List<AssignedService> getAllAssignedServices()
    {
        return assignedServiceRepository.findAll();
    }

    //Gets a list of assigned services for an employee
    public List<AssignedService> getAssignedServicesByUserName(String userName)
    {
        return assignedServiceRepository.findAllByUserNameEquals(userName);
    }
}
