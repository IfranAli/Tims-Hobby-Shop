package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Sale_Repository extends CrudRepository<Sale, Long> {

    @Query(value = "SELECT * FROM sale c WHERE c.fk_customer_id = :id", nativeQuery = true)
    List<Sale> findSalesByCustomerId(@Param("id") Long id);
}
