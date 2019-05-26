package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.CustomerSubjectInterest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerSubjectInterest_Repository extends CrudRepository<CustomerSubjectInterest, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from customer_subject_interest where fk_customer_id = :id", nativeQuery = true)
    void deleteByCustomerID(@Param("id") Long id);

    @Query(value = "select si.fk_customer_id as customerID, s.id as subjectAreaID, s.name as subjectAreaName from customer_subject_interest si right join subject_area s on si.fk_subject_area_id = s.id and si.fk_customer_id = :id", nativeQuery = true)
    List<CustomerSubjectInterestInterface> getCustomerSubjectInterestById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from customer_subject_interest where fk_subject_area_id = :id", nativeQuery = true)
    void deleteCustomerSubjectAreaBySubjectAreaID(@Param("id") Long id);

    public static interface CustomerSubjectInterestInterface {
        Long getCustomerID();
        Long getSubjectAreaID();
        String getSubjectAreaName();
    }
}
