package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    Person findPersonByUserName(String userName);
    Person deleteByUserName(String userName);
    Person findAllByAdminCheckedIsTrue();


}
