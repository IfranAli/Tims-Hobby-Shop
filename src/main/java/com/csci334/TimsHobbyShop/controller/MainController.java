package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.model.Sale;
import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private Customer_Repository customerRepository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Sale_Repository sale_repository;

    @GetMapping("/test")
    public String index(Model model) {

        Iterable<Sale> x = sale_repository.findAll();

        model.addAttribute("items", item_repository.findAll());

        return "item";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
