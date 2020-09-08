package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {

    Admin findAdminByName(String name);

}
