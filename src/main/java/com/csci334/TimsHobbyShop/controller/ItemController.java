
package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Sale;
import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.model.SaleLineItem;
import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/Item")
public class ItemController {
    @Autowired
    private Item_Repository item_repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderItemPageById(@PathVariable(name = "id", value = "id") int id, Model model) {
        Optional<Item> optionalItem = item_repository.findById(id);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            model.addAttribute("item", item);
            model.addAttribute("title", "Item");
            model.addAttribute("content", "item");
            return "index";
        }
        return "index";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Item> getAll() {
        return item_repository.findAll();
    }

}
