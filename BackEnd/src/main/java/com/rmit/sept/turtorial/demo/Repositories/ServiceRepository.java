package com.rmit.sept.turtorial.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rmit.sept.turtorial.demo.model.Service;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long>
{
    Service findByiD(long iD);

    List<Service> findAll();
}
