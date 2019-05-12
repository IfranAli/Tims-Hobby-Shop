package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.ModelType;
import com.csci334.TimsHobbyShop.model.SubjectArea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ModelType_Repository extends CrudRepository<ModelType, Long> {

    @Query(value = "select * from model_type where model_type.name = :name", nativeQuery = true)
    ModelType findByName(@Param("name") String name);

}
