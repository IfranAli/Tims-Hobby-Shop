package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.SubjectArea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectArea_Repository extends CrudRepository<SubjectArea, Long> {
    @Query(value = "select * from subject_area where subject_area.name = :name", nativeQuery = true)
    SubjectArea findByName(@Param("name") String name);
}
