package com.rmit.sept.turtorial.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rmit.sept.turtorial.demo.model.EmpService;

import java.util.List;

@Repository
public interface EmpServiceRepository extends CrudRepository<EmpService, Long>
{
    EmpService findByiD(long iD);

    List<EmpService> findAll();
}
