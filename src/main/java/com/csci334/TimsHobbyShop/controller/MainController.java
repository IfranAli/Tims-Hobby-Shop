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
    public String test(Model model) {
        model.addAttribute("title", "DevTest");
        model.addAttribute("content", "test");
        return "index";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @GetMapping(path="/")
    public String index(Model model) {
        model.addAttribute("title", "index");
        return "index";
    }

    @GetMapping(path="/Catalogue")
    public String Catalogue(Model model) {
        model.addAttribute("title", "Catalogue");
        model.addAttribute("content", "catalogue");
        return "index";
    }
    @GetMapping(path="/Login")
    public String Login(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("content", "login");
        return "index";
    }
    @GetMapping(path="/Register")
    public String Register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("content", "register");
        return "index";
    }

}
