package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Person_Repository extends CrudRepository<Person, Long> {

    @Query(value = "SELECT * FROM person p WHERE p.username = :username", nativeQuery = true)
    Person findByUsername(@Param("username") String username);
}
