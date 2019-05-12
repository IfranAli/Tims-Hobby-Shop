package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Customer_Repository extends CrudRepository<Customer, Long> {
    @Query(value = "select * from customer c where c.fk_person_id = :id", nativeQuery = true)
    Customer findByPersonId(@Param("id") Long id);

    @Query(value = "select * from customer c where c.fk_person_id in (select id from person p where p.name like %:name%)", nativeQuery = true)
    List<Customer> searchByName(@Param("name") String name);
}
