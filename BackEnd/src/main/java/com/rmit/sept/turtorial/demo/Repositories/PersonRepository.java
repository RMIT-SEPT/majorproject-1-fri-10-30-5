package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    This interface contains all queries that will be executed
    on the Person table in the database
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>
{

    //This query finds a person based on the userName passed in
    Person findPersonByUserName(String userName);

    //This query finds an employee based on the username and userType passed in
    Person findPersonByUserNameAndUserTypeEquals(String userName, String userType);

    //This query finds all employees stored in the database
    List<Person> findAllByUserTypeEquals(String userType);

    //This query determines if person exists by the username passed in
    Boolean existsByUserNameEquals(String userName);
}
