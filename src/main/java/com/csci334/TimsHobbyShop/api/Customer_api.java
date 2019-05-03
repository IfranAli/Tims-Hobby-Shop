package com.csci334.TimsHobbyShop.api;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/")
public class Customer_api {
    @Autowired
    private Customer_Repository customerRepository;

    @GetMapping(path="/api/customer/add")
    public @ResponseBody
    String addNewUCustomer (@RequestParam String name, @RequestParam String address, @RequestParam String password) {
		//Customer n = new Customer();
        //n.setName(name);
        //n.setAddress(address);
        //n.setPassword(password);
        //customerRepository.save(n);
        return "Saved";
    }

    @DeleteMapping
    String delete_CustomerById (@RequestParam int id) {
        customerRepository.deleteById(id);
        return "Deleted";
    }

    @GetMapping(path="/api/customer")
    public @ResponseBody Iterable<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
