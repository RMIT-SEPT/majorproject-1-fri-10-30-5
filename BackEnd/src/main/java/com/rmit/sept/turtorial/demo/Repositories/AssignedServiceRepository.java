package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.AssignedService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    This interface contains all queries to be executed
    on the Assigned Services database
 */
@Repository
public interface AssignedServiceRepository extends CrudRepository<AssignedService, Long>
{
    //Find all assigned services for a user
    List<AssignedService> findAllByUserNameEquals(String userName);

    //Finds all assigned services for a service
    List<AssignedService> findAllByServiceIDEquals(long serviceID);

    //Finds all assigned services in the database
    List<AssignedService> findAll();

    //Determines whether an employee has been assigned a service
    Boolean existsByServiceIDEqualsAndUserNameEquals(long serviceID, String userName);
}