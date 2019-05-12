
package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(path="/Item")
public class ItemController {
    @Autowired
    private Item_Repository item_repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderItemPageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Item> o = item_repository.findById(id);

        if (o.isPresent()) {
            Item i = o.get();
            model.addAttribute("item", i);
            model.addAttribute("title", i.getName());
            model.addAttribute("Area", "Item");
            model.addAttribute("Sub_Page", "Item");
        }
        return "Master";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        if(query != null && !query.isEmpty()) {
            model.addAttribute("items", item_repository.searchByName(query));
            model.addAttribute("query", query);
        } else {
            model.addAttribute("items", item_repository.findAll());
        }
        model.addAttribute("title", "Items");
        model.addAttribute("Area", "Item");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }
}
