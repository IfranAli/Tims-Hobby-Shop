package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.LoginForm;
import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Person;
import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import com.csci334.TimsHobbyShop.repository.Person_Repository;
import com.csci334.TimsHobbyShop.repository.UserDetails_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private Person_Repository personRepository;
    @Autowired
    private Customer_Repository customerRepository;
    @Autowired
    public UserDetails_Service userDetails_service;

    @GetMapping()
    public String index(Model model) {
        return "redirect:/DevTest";
    }

    @GetMapping("/DevTest")
    public String DevTest(Model model, Authentication authentication) {
        model.addAttribute("title", "DevTest");
        model.addAttribute("Area", "Other");
        model.addAttribute("Sub_Page", "DevTest");
        return "Master";
    }

    @GetMapping(path="/AboutUs")
    public String AboutUs(Model model, Authentication authentication) {
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

    @GetMapping(path="/Logout")
    public String Logout(Model model, Authentication authentication) {
        authentication.setAuthenticated(false);
        return "redirect:/Login";
    }

    @GetMapping(path="/Login")
    public String Login(Model model, Authentication authentication) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("title", "Login");
        model.addAttribute("Area", "Other");
        model.addAttribute("Sub_Page", "Login");
        return "Master";
    }
    @PostMapping(path="/Login")
    public String LoginProcess(HttpServletRequest req, Model model, @Valid LoginForm form, BindingResult bindingResult, Authentication authentication) {
        model.addAttribute("title", "Login");
        model.addAttribute("Area", "Other");
        model.addAttribute("Sub_Page", "Login");

        if (bindingResult.hasErrors()) return "Master";

        Person p = personRepository.findByUsername(form.getUsername());

        if (p != null) {
            if (form.getPassword().equals(p.getPassword())) {
                // Successful match
				SecurityContext sc = SecurityContextHolder.getContext();
				sc.setAuthentication(new UsernamePasswordAuthenticationToken(p.getUsername(), p.getPassword()));
                HttpSession session = req.getSession(true);
                session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

                switch (p.getRole()){
                    case "ADMIN":
                        return ("redirect:/Admin/");
                    case "EMPLOYEE":
                        return "redirect:/Employee/";
                    case "USER":
                        return ("redirect:/User/");
                }
                bindingResult.addError(new FieldError("loginForm", "username", "User is not a Customer"));
            }
            bindingResult.addError(new FieldError("loginForm", "password", "Incorrect password"));
        } else {
            bindingResult.addError(new FieldError("loginForm", "username", "Username not found"));
        }
        return "Master";
    }
}
