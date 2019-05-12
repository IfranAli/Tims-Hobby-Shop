package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.CustomerSubjectInterest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CustomerSubjectInterest_Repository extends CrudRepository<CustomerSubjectInterest, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from customer_subject_interest where fk_customer_id = :id", nativeQuery = true)
    void deleteByCustomerID(@Param("id") Long id);
}
