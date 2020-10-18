package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    This interface contains all queries that will be executed
    on the Person database
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>
{
    //Finds a person in the database
    Person findPersonByUserName(String userName);

    //Finds a person of a certain userType
    Person findPersonByUserNameAndUserTypeEquals(String userName, String userType);

    //Finds all people of a certain userType
    List<Person> findAllByUserTypeEquals(String userType);

    //Determines if a person exists in the database
    Boolean existsByUserNameEquals(String userName);
}