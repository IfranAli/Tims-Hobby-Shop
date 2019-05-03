package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Sale;
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
@RequestMapping(path="/Customer")
public class CustomerController {
    @Autowired
    private Customer_Repository customerRepository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Sale_Repository sale_repository;

    @RequestMapping(value = "/sales/{id}", method = RequestMethod.GET)
    public String RenderSalePageById(@PathVariable(name = "id", value = "id") int id, Model model) {
        Optional<Sale> optionalSale = sale_repository.findById(id);

        if (optionalSale.isPresent()) {
            Sale sale = optionalSale.get();

            model.addAttribute("sale", sale);
            model.addAttribute("title", "Sale");
            model.addAttribute("Area", "Sale");
            model.addAttribute("Sub_Page", "Sale");
        }
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderCustomerPageById(@PathVariable(name = "id", value = "id") int id, Model model) {
        Optional<Customer> optionalItem = customerRepository.findById(id);

        if (optionalItem.isPresent()) {
            Customer c = optionalItem.get();
            model.addAttribute("customer", c);
            model.addAttribute("title", c.getPerson().getName());
            model.addAttribute("Area", "Customer");
            model.addAttribute("Sub_Page", "Profile");
        }
        return "Master";
    }

    /* Returns all customers in the database */
    @RequestMapping(method = RequestMethod.GET)
    public String getAll( Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("title", "Customer");
        model.addAttribute("Area", "Customer");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }
}
