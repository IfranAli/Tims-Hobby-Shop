package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface Supplier_Repository extends CrudRepository<Supplier, Long> {
    @Query(value = "select * from supplier c where c.company_name like %:name%", nativeQuery = true)
    List<Supplier> searchByCompanyName(@Param("name") String name);
}
