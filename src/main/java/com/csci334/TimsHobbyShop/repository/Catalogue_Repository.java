package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Catalogue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface Catalogue_Repository extends CrudRepository<Catalogue, Long> {

    @Query(value = "SELECT * FROM catalogue p WHERE p.fk_supplier_id = :id", nativeQuery = true)
    List<Catalogue> findBySupplierId(@Param("id") Long id);
}
