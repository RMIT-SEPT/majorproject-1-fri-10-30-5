package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.PersonRepository;
import com.rmit.sept.turtorial.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/*
    This class is the service which will be used by the Person Controller
    to retrieve data stored in the database through the use of the Assigned
    Service Repository. This class also contains the majority of the business logic.
 */
@Service
public class PersonService
{
    //An instance of the Person Repository
    @Autowired
    private PersonRepository personRepository;

    //Length of valid phone number
    private final int PHONE_LENGTH = 10;

    //This method adds a person if they do not already exist
    public Person addPerson(Person person)
    {
        if(validateFields(person))
            return null;

        return personRepository.save(person);
    }

    //This method gets a person based on the userName passed in
    public Person getPersonByUserName(String userName)
    {
        if (userName == null || !personRepository.existsByUserNameEquals(userName))
            return null;

        return personRepository.findPersonByUserName(userName);
    }

    //This method updates a pre-existing person object
    public Person updatePerson(Person person)
    {
        if (!personRepository.existsByUserNameEquals(person.getUserName()))
            return null;

        Person person1 = personRepository.findPersonByUserName(person.getUserName());
        if (person1 != null && !validateFields(person))
        {
            person1.setFirstName(person.getFirstName());
            person1.setLastName(person.getLastName());
            person1.setPassword(person.getPassword());
            person1.setPhone(person.getPhone());
            person1.setAddress(person.getAddress());
            person1.setUpdated_At(new Date());
            person1.setUserType(person.getUserType());
            return personRepository.save(person1);
        }

        return null;
    }

    //This method gets a list of employees
    public List<Person> getEmployees()
    {
        return personRepository.findAllByUserTypeEquals("employee");
    }

    //This method gets a list of customers
    public List<Person> getCustomers() { return personRepository.findAllByUserTypeEquals("customer"); }

    //This method validates each field when updating
    private boolean validateFields(Person person)
    {
        if (person.getFirstName().isEmpty() || !person.getFirstName().matches("^[a-zA-Z ]*$"))
            return false;
        if (person.getLastName().isEmpty() || !person.getLastName().matches("^[a-zA-Z ]*$"))
            return false;
        if (person.getAddress().isEmpty())
            return false;
        if (person.getPhone().length() != PHONE_LENGTH ||
                !Pattern.matches("^[0-9 ]*$", person.getPhone()))
            return false;
        if (!person.getUserType().matches("admin") || !person.getUserType().matches("employee")
                || !person.getUserType().matches("customer"))
            return false;

        return true;
    }
}
