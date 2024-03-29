
package com.rmit.sept.turtorial.demo.Repositories;

import com.rmit.sept.turtorial.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String username);
    User getById(Long id);
    User deleteByUserName(String username);
}