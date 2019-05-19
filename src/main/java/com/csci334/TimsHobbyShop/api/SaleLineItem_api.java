package com.csci334.TimsHobbyShop.api;

import com.csci334.TimsHobbyShop.DTO.ItemDTO;
import com.csci334.TimsHobbyShop.DTO.SaleLineItemDTO;
import com.csci334.TimsHobbyShop.model.Sale;
import com.csci334.TimsHobbyShop.model.SaleLineItem;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.SaleLineItem_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping(path="/api/SaleLineItem")
public class SaleLineItem_api {
    @Autowired
    private SaleLineItem_Repository saleLineItem_repository;

    @GetMapping()
    public @ResponseBody
    String GetAll() {
        return toDTO(saleLineItem_repository.findAll());
    }

    @GetMapping("/BySaleId")
    public @ResponseBody
    String GetAllBySaleId(@RequestParam Long id) {
        return toDTO(saleLineItem_repository.findBySaleId(id));
    }

    private String toDTO(Iterable<SaleLineItem> items) {
        ArrayList<SaleLineItemDTO> saleLineItemDTOs = new ArrayList<>();
        items.forEach(i -> saleLineItemDTOs.add(new SaleLineItemDTO(i)));
        try {
            return new ObjectMapper().writeValueAsString(saleLineItemDTOs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}