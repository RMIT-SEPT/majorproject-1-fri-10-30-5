package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.PersonRepository;
import com.rmit.sept.turtorial.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(Person person) {

        return personRepository.save(person);
    }


    public Person getPersonByUserName(String userName) {

        return personRepository.findPersonByUserName(userName);
    }

    //put services
    public Person updatePerson(Person person) {
        Person person1 = personRepository.findPersonByUserName(person.getUserName());
        if (person1 != null)
        {
            //person1.setUserName(person.getUserName());
            person1.setFirstName(person.getFirstName());
            person1.setLastName(person.getLastName());
            person1.setPassword(person.getPassword());
            person1.setPhone(person.getPhone());
            person1.setAddress(person.getAddress());
            person1.setUpdated_At(new Date());
            person1.setEmployeeCheck(person.getEmployeeCheck());
            person1.setCustomerCheck(person.getCustomerCheck());
            person1.setAdminCheck(person.getAdminCheck());
            return personRepository.save(person1);
        }
        else{
            return null;
        }

    }

    //delete services
    public String deletePerson(String userName){
        personRepository.deleteByUserName(userName);
        return "Person " + userName + " has been successfully removed";
    }

    //Get employees list
    public List<Person> getEmployees()
    {
        return personRepository.findAllByEmployeeCheckIsTrue();
    }

    //Get employees list
    public List<Person> getCustomers()
    {
        return personRepository.findAllByCustomerCheckIsTrue();
    }
}
