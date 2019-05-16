package com.csci334.TimsHobbyShop.api;

import com.csci334.TimsHobbyShop.DTO.ItemDTO;
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

    @GetMapping()
    public @ResponseBody
    String get_all_items() throws JsonProcessingException {
        ArrayList<ItemDTO> items = new ArrayList<>();
        itemRepository.findAll().forEach(i -> items.add(new ItemDTO(i.getId(), i.getName(), i.getDescription(), i.getAvailability(), i.getRetailPrice(), i.getStock())));
        return new ObjectMapper().writeValueAsString(items);
    }
}
