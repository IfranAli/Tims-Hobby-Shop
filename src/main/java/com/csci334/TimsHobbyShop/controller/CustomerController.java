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

        return "item";
    }

    @RequestMapping(value = "/sales/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Sale>> findById(@PathVariable(name = "id", value = "id") int id) {
        Optional<Sale> optionalSale = sale_repository.findById(id);

        if (optionalSale.isPresent()) {
            Sale sale = optionalSale.get();

            List<SaleLineItem> saleLineItems = sale.getSale_line_items();
            return new ResponseEntity(sale, HttpStatus.OK);
        }

        return new ResponseEntity("not found", HttpStatus.NOT_FOUND);
        //UserDetails userDetail = userService.findByEmail(email);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
