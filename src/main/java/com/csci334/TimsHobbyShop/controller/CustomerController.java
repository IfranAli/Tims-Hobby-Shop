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

    @GetMapping("/sales")
    public String index(Model model) {
        Iterable<Sale> x = sale_repository.findAll();
        model.addAttribute("items", item_repository.findAll());
        model.addAttribute("content", "item");
        return "index";
    }

    @RequestMapping(value = "/sales/{id}", method = RequestMethod.GET)
    public String RenderSalePageById(@PathVariable(name = "id", value = "id") int id, Model model) {
        Optional<Sale> optionalSale = sale_repository.findById(id);

        if (optionalSale.isPresent()) {
            Sale sale = optionalSale.get();
            //List<SaleLineItem> saleLineItems = sale.getSale_line_items();

            // return new ResponseEntity(sale, HttpStatus.OK);
            model.addAttribute("sale", sale);
            model.addAttribute("title", "Sale");
            model.addAttribute("content", "sale");
            return "index";
        }

        return "index";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    /* Returns all customers in the database */
    @GetMapping()
    public @ResponseBody Customer getAllCusto() {
		Optional<Customer> o = customerRepository.findById(0);
		if (o.isPresent()) {
			return o.get();
		}

		return null;
    }

	//@GetMapping()
    public String All_Customers(Model model) {
        Iterable<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("title", "Catalogue");
        model.addAttribute("content", "customer");
        return "index";
    }
}
