package com.rmit.sept.turtorial.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rmit.sept.turtorial.demo.model.Service;

import java.util.List;

/*
    This interface contains all queries that will be executed
    upon the Service table in the database
 */
@Repository
public interface ServiceRepository extends CrudRepository<Service, Long>
{
    //This query finds a service based on the service ID passed in
    Service findByServiceId(long serviceID);

    //This query finds all services stored in the database
    List<Service> findAll();
}