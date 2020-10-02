package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.AssignedServiceRepository;
import com.rmit.sept.turtorial.demo.Repositories.PersonRepository;
import com.rmit.sept.turtorial.demo.model.AssignedService;
import com.rmit.sept.turtorial.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignedServiceService
{
    @Autowired
    AssignedServiceRepository assignedServiceRepository;

    @Autowired
    PersonRepository personRepository;

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

    //Gets a list of employees assigned to a particular service
    public List<Person> getAllEmployeesByAssignedService(long serviceId){
       List<AssignedService> services = assignedServiceRepository.findAllByServiceIdEquals(serviceId);
       List<String> employeeUserNames = new ArrayList<>();

        for (AssignedService service: services)
        {
            employeeUserNames.add(service.getUserName());
        }

        List<Person> employees = new ArrayList<>();

        for (String userName: employeeUserNames)
        {
            employees.add(personRepository.findPersonByUserNameAndEmployeeCheckIsTrue(userName));
        }

        return employees;
    }
}
