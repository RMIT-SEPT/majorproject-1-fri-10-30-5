package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.AssignedService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    This interface contains all queries to be executed
    on the Assigned Services table in the database
 */
@Repository
public interface AssignedServiceRepository extends CrudRepository<AssignedService, Long>
{
    //This query gives a list of assigned services based on the userName passed in
    List<AssignedService> findAllByUserNameEquals(String userName);

    //This query gives a list of assigned services based on the serviceId passed in
    List<AssignedService> findAllByServiceIDEquals(long serviceID);

    //This query gives a list of all assigned services in the database
    List<AssignedService> findAll();

    //This query determines whether an employee has been assigned a service
    Boolean existsByServiceIDEqualsAndUserNameEquals(long serviceID, String userName);
}