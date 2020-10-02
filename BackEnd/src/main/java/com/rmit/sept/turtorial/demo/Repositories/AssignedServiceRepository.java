package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.AssignedService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignedServiceRepository extends CrudRepository<AssignedService, Long>
{
    List<AssignedService> findAllByUserNameEquals(String userName);

    List<AssignedService> findAllByServiceIdEquals(long serviceId);

    List<AssignedService> findAll();
}
