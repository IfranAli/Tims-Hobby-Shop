package com.csci334.TimsHobbyShop.api;

import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/api/item")
public class Item_api {
    @Autowired
    private Item_Repository itemRepository;

    @GetMapping("/add")
    public @ResponseBody
    String add_new_item (@RequestParam String name, @RequestParam String description, @RequestParam double price, @RequestParam int stock) {
        Item i = new Item();
        i.setName(name);
        i.setDescription(description);
        i.setRetailPrice(price);
        i.setStock(stock);

        itemRepository.save(i);
        return "Saved";
    }

    @DeleteMapping("/delete")
    String delete_item_by_id (@RequestParam Long id) {
        itemRepository.deleteById(id);
        return "Deleted";
    }

    @GetMapping()
    public @ResponseBody Iterable<Item> get_all_items() {
        return itemRepository.findAll();
    }
}
