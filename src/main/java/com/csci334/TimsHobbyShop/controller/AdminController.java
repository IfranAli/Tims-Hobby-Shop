package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.Person_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/Admin")
public class AdminController {
    @Autowired
    private Customer_Repository customerRepository;
    @Autowired
    private Person_Repository personRepository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Sale_Repository sale_repository;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll( Model model) {
        model.addAttribute("title", "Admin Dashboard");
        model.addAttribute("Area", "Admin");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }
}
