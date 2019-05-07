package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Customer_Repository extends CrudRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer c WHERE c.fk_person_id = :id", nativeQuery = true)
    Customer findByPersonId(@Param("id") Long id);
}
