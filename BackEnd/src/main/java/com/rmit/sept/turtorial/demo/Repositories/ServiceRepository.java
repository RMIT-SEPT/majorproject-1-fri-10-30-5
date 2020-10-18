package com.rmit.sept.turtorial.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rmit.sept.turtorial.demo.model.Service;

import java.util.List;

/*
    This interface contains all queries that will be executed
    upon the Service database
 */
@Repository
public interface ServiceRepository extends CrudRepository<Service, Long>
{
    //Finds a service by an ID
    Service findByServiceId(long serviceID);

    //Finds all services stored in the database
    List<Service> findAll();
}