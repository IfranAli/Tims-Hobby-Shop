package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.StoreForm;
import com.csci334.TimsHobbyShop.DTO.ItemDTO;
import com.csci334.TimsHobbyShop.DTO.SupplierItemDTO;
import com.csci334.TimsHobbyShop.model.Store;
import com.csci334.TimsHobbyShop.model.Store;
import com.csci334.TimsHobbyShop.model.SupplierItem;
import com.csci334.TimsHobbyShop.repository.Store_Repository;
import com.csci334.TimsHobbyShop.repository.Store_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.SupplierItem_Repository;
import com.csci334.TimsHobbyShop.repository.Supplier_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/Store")
public class StoreController {
    @Autowired
    private Store_Repository store_repository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private SupplierItem_Repository supplierItem_repository;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        model.addAttribute("title", "Stores");
        model.addAttribute("Area", "Store");
        model.addAttribute("Sub_Page", "Index");

        if(query != null && !query.isEmpty()) {
            model.addAttribute("stores", store_repository.findAll());
            model.addAttribute("query", query);
        } else {
			model.addAttribute("stores", store_repository.findAll());
		}
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderStorePageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Store> optionalStore = store_repository.findById(id);

        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();
            model.addAttribute("store", store);
            model.addAttribute("title", store.getName());
            model.addAttribute("Area", "Store");
            model.addAttribute("Sub_Page", "Store");
        }
        return "Master";
    }

    @RequestMapping(value = "/{cid}/Delete", method = RequestMethod.GET)
    public String DeleteStore(@PathVariable(name = "cid", value = "cid") Long storeID, Model model) {
        Optional<Store> optionalStore = store_repository.findById(storeID);
        if (optionalStore.isPresent()) {
            store_repository.delete(optionalStore.get());
        }
        return "redirect:/Store";
    }

    @RequestMapping(value = "/{cid}/Edit", method = RequestMethod.GET)
    public String EditStore(@PathVariable(name = "cid", value = "cid") Long storeID, Model model) {
        Optional<Store> optionalStore= store_repository.findById(storeID);
        if (optionalStore.isPresent()) {
            model.addAttribute("storeForm", new StoreForm(optionalStore.get()));
            return CreateStore(model);
        }
        return "redirect:/Store";
    }

    @GetMapping(path="/Create")
    public String CreateStore(Model model) {
        model.addAttribute("title", "Edit Store");
        model.addAttribute("Area", "Store");
        model.addAttribute("Sub_Page", "StoreForm");

		// TODO: fix this controller
//        ArrayList<ItemDTO> itemDTOs = new ArrayList<>();
//        item_repository.findAll().forEach(i -> itemDTOs.add(new ItemDTO(i)));
//        model.addAttribute("items", itemDTOs);

        if (!model.containsAttribute("storeForm")) {
            StoreForm storeForm = new StoreForm();
            model.addAttribute("title", "Create Store");
            model.addAttribute("storeForm", storeForm);
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateStore(Model model, @Valid StoreForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create Store");
        model.addAttribute("Area", "Store");
        model.addAttribute("Sub_Page", "StoreForm");

        if (bindingResult.hasErrors()) return CreateStore(model);

        // Save in database.
        Store store = new Store();

        // Are we saving an existing supplier?
        if(form.getId() != null) {
            Optional<Store> optional = store_repository.findById(form.getId());
            if (optional.isPresent()) {
                store = optional.get();
            }
        }

        store.setName(form.getName());
        store.setAddress(form.getAddress());
        store_repository.save(store);

        return "redirect:/Store/" + store.getId();
    }
}
