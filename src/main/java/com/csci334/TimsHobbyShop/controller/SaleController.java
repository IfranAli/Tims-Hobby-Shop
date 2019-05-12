package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Sale;
import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/Sale")
public class SaleController {
    @Autowired
    private Customer_Repository customerRepository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Sale_Repository sale_repository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("sales", sale_repository.findAll());
        model.addAttribute("title", "Sales");
        model.addAttribute("Area", "Sale");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderSalePageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
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
}
