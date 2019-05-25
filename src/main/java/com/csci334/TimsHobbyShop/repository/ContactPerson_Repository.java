package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.ContactPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactPerson_Repository extends CrudRepository<ContactPerson, Long> {
    @Query(value = "select * from contact_person c where c.fk_supplier_id = :id", nativeQuery = true)
    List<ContactPerson> findAllBySupplierId(@Param("id") Long id);
}
