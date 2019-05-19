package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.DTO.SaleLineItemDTO;
import com.csci334.TimsHobbyShop.model.SaleLineItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SaleLineItem_Repository extends CrudRepository<SaleLineItem, Long> {
    @Query(value = "SELECT * FROM `sale_line_item` where fk_sale_id = :id", nativeQuery = true)
    List<SaleLineItem> findBySaleId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from sale_line_item where fk_sale_id = :id", nativeQuery = true)
    void deleteAllBySaleID(@Param("id") Long id);
}
