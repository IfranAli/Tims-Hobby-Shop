package com.csci334.TimsHobbyShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class MainController {
    @GetMapping()
    public String index(Model model) {
        return DevTest(model);
    }

    @GetMapping("/DevTest")
    public String DevTest(Model model) {
        model.addAttribute("title", "DevTest");
        model.addAttribute("Area", "Other");
        model.addAttribute("Sub_Page", "DevTest");
        return "Master";
    }

    @GetMapping(path="/AboutUs")
    public String AboutUs(Model model) {
        model.addAttribute("title", "AboutUs");
        model.addAttribute("Area", "Other");
        model.addAttribute("Sub_Page", "AboutUs");
        return "Master";
    }
    @GetMapping(path="/Catalogue")
    public String Catalogue(Model model) {
        model.addAttribute("title", "Catalogue");
        model.addAttribute("Area", "Catalogue");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }
    @GetMapping(path="/Login")
    public String Login(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("Area", "Other");
        model.addAttribute("Sub_Page", "Login");
        return "Master";
    }
    @GetMapping(path="/Register")
    public String Register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("Area", "Other");
        model.addAttribute("Sub_Page", "Register");
        return "Master";
    }

}
