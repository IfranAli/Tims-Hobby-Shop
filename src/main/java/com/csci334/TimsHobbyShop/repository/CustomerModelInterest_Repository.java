package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.CustomerModelInterest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerModelInterest_Repository extends CrudRepository<CustomerModelInterest, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from customer_model_interest where fk_customer_id = :id", nativeQuery = true)
    void deleteByCustomerID(@Param("id") Long id);

    @Query(value = "select si.fk_customer_id as customerID, s.id as modelTypeID, s.name as modelTypeName from customer_model_interest si right join model_type s on si.fk_model_type_id = s.id and si.fk_customer_id = :id", nativeQuery = true)
    List<CustomerModelInterestInterface> getCustomerModelInterestById(@Param("id") Long id);

    public static interface CustomerModelInterestInterface {
        Long getCustomerID();
        Long getModelTypeID();
        String getModelTypeName();
    }
}
