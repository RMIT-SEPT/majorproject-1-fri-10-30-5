package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.AssignedServiceRepository;
import com.rmit.sept.turtorial.demo.Repositories.PersonRepository;
import com.rmit.sept.turtorial.demo.model.AssignedService;
import com.rmit.sept.turtorial.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
    This class is the service which will be used by the Assigned Service
    Controller to retrieve data stored in the database through the use of the
    Assigned Service Repository. This class also contains the majority of the business logic.
 */
@Service
public class AssignedServiceService
{
    //An instance of the Assigned Service Repository
    @Autowired
    AssignedServiceRepository assignedServiceRepository;

    //An instance of the Person Repository
    @Autowired
    PersonRepository personRepository;

    //Adds an assigned service if it doesn't already exist
    public AssignedService addAssignedService(AssignedService assignedService)
    {
        if(assignedServiceRepository.existsByServiceIDEqualsAndUserNameEquals
                (assignedService.getServiceID(), assignedService.getUserName()))
            return null;

        return assignedServiceRepository.save(assignedService);
    }

    //Gets a list of all assigned services from the database
    public List<AssignedService> getAllAssignedServices() { return assignedServiceRepository.findAll(); }

    //Gets a list of assigned services for an employee
    public List<AssignedService> getAssignedServicesByUserName(String userName) { return assignedServiceRepository.findAllByUserNameEquals(userName); }

    //Gets a list of employees assigned to a particular service
    public List<Person> getAllEmployeesByAssignedService(long serviceId)
    {
        List<AssignedService> services = assignedServiceRepository.findAllByServiceIDEquals(serviceId);
        List<String> employeeUserNames = new ArrayList<>();
        List<Person> employees = new ArrayList<>();

        //Find all employee username who have been assigned a service
        for (AssignedService service: services)
        {
            employeeUserNames.add(service.getUserName());
        }

        //Use usernames to find all employees
        for (String userName: employeeUserNames)
        {
            employees.add(personRepository.findPersonByUserNameAndUserTypeEquals
                    (userName, "employee"));
        }

        return employees;
    }
}