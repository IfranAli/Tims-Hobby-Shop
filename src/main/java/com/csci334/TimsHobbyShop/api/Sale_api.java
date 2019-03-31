package com.csci334.TimsHobbyShop.api;

import com.csci334.TimsHobbyShop.model.Sale;
import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping(path="/api/sale")
public class Sale_api {
    @Autowired
    private Sale_Repository saleRepository;
    @Autowired
    private Customer_Repository customerRepository;

    @GetMapping("/add")
    public @ResponseBody
    String add_new_sale (@RequestParam @DateTimeFormat(pattern="yyyyMMdd") Date order_date, @RequestParam String status, @RequestParam double total, @RequestParam int customer_id) {
        Sale s = new Sale();

        customerRepository.findById(customer_id).ifPresent(s::setCustomer);

        s.setrderDate(new java.sql.Date(order_date.getTime()));
        s.setStatus(status);
        s.setTotal(total);
        saleRepository.save(s);
        return "Saved";
    }

    @DeleteMapping("/delete")
    String delete_sale_by_id (@RequestParam int id) {
        saleRepository.deleteById(id);
        return "Deleted";
    }

    @GetMapping()
    public @ResponseBody Iterable<Sale> get_all_sales() {
        return saleRepository.findAll();
    }
}
