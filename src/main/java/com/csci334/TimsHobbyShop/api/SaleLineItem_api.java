package com.csci334.TimsHobbyShop.api;

import com.csci334.TimsHobbyShop.model.SaleLineItem;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.SaleLineItem_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/api/SaleLineItem")
public class SaleLineItem_api {
    @Autowired
    private SaleLineItem_Repository saleLineItem_repository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Sale_Repository sale_repository;

    @GetMapping("/add")
    public @ResponseBody
    String add_new_SaleLineItem (@RequestParam int quantity, @RequestParam Long item_id, @RequestParam Long sale_id) {
        SaleLineItem i = new SaleLineItem();
        i.setQuantity(quantity);
        sale_repository.findById(sale_id).ifPresent(i::setSale);
        item_repository.findById(item_id).ifPresent(i::setItem);
        saleLineItem_repository.save(i);
        return "Saved";
    }

    @DeleteMapping("/delete")
    String delete_SaleLineItem_by_id (@RequestParam Long id) {
        saleLineItem_repository.deleteById(id);
        return "Deleted";
    }

    @GetMapping()
    public @ResponseBody Iterable<SaleLineItem> get_all_SaleLineItems() {
        return saleLineItem_repository.findAll();
    }
}