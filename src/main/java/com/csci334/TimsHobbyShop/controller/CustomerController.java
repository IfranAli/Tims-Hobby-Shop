package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.RegisterForm;
import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Person;
import com.csci334.TimsHobbyShop.model.Sale;
import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.Person_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path="/Customer")
public class CustomerController {
    @Autowired
    private Customer_Repository customerRepository;
    @Autowired
    private Person_Repository personRepository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Sale_Repository sale_repository;

    @RequestMapping(value = "/sales/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderCustomerPageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
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

    @GetMapping(path="/Register")
    public String Register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        model.addAttribute("title", "Register");
        model.addAttribute("Area", "Other");
        model.addAttribute("Sub_Page", "Register");
        return "Master";
    }

    @PostMapping("/Register")
    public String CreateCustomer(Model model, @Valid RegisterForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Register");
        model.addAttribute("Area", "Other");
        model.addAttribute("Sub_Page", "Register");

        if (bindingResult.hasErrors()) return "Master";

        // Check password and confirm password match
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            bindingResult.addError(new FieldError("registerForm", "passwordConfirm",
                    "Password and ConfirmPassword must match"));
        }

        // Check if username already exists in the database
        if (personRepository.findByUsername(form.getUsername()) != null) {
            bindingResult.addError(new FieldError("registerForm", "username",
                    "Username exists"));
        }

        if (bindingResult.hasErrors()) {
            return "Master";
        }

        // Save customer in database.
        Person person = new Person();
        person.setName(form.getName());
        person.setUsername(form.getUsername());
        person.setPassword(form.getPassword());
        person.setEmail(form.getEmail());
        person.setPhone(form.getPhone());
        personRepository.save(person);

        Customer customer = new Customer();
        customer.setAddress(form.getAddress());
        customer.setBalance(form.getBalance());
        customer.setCreditline(form.getCreditline());
        customer.setPerson(person);
        customerRepository.save(customer);

        model.addAttribute("Sub_Page", "Login");
        return "redirect:/Login";
    }
}
