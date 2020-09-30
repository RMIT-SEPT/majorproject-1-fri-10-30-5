package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    Person findPersonByUserName(String userName);

    Person deleteByUserName(String userName);

//    Person findAllByAdminCheckIsTrue();
//    List<Person> findAllByCustomerCheckIsTrue();

    List<Person> findAllByEmployeeCheckIsTrue();
}
