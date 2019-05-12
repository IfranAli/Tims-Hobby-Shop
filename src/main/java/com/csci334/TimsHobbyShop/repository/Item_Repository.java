package com.csci334.TimsHobbyShop.repository;

import com.csci334.TimsHobbyShop.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Item_Repository extends CrudRepository<Item, Long> {
    @Query(value = "select * from item i where i.name like %:name%", nativeQuery = true)
    List<Item> searchByName(@Param("name") String name);
}
