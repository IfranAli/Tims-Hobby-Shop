package com.csci334.TimsHobbyShop.api;

import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping(path="/api/item")
public class Item_api {
    @Autowired
    private Item_Repository itemRepository;

    class ItemDTO {
        public ItemDTO(Long id, String name, String description, String availability, double retail_price, int stock) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.availability = availability;
            this.retail_price = retail_price;
            this.stock = stock;
        }
        public Long id;
        public String name, description, availability;
        public double retail_price;
        public int stock;
    }

    @GetMapping()
    public @ResponseBody String get_all_items() throws JsonProcessingException {
        ArrayList<ItemDTO> items = new ArrayList<>();
        itemRepository.findAll().forEach(i -> items.add(new ItemDTO(i.getId(), i.getName(), i.getDescription(), i.getAvailability(), i.getRetailPrice(), i.getStock())));
        return new ObjectMapper().writeValueAsString(items);
    }
}
