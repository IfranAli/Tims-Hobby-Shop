
package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.ItemForm;
import com.csci334.TimsHobbyShop.api.Item_api;
import com.csci334.TimsHobbyShop.api.SaleLineItem_api;
import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.model.ModelType;
import com.csci334.TimsHobbyShop.model.SubjectArea;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.ModelType_Repository;
import com.csci334.TimsHobbyShop.repository.Store_Repository;
import com.csci334.TimsHobbyShop.repository.SubjectArea_Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path="/Item")
public class ItemController {
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private ModelType_Repository modelType_repository;
    @Autowired
    private SubjectArea_Repository subjectArea_repository;
    @Autowired
    private Store_Repository store_repository;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        if(query != null && !query.isEmpty()) {
            model.addAttribute("items", item_repository.searchByName(query));
            model.addAttribute("query", query);
        } else {
            model.addAttribute("items", item_repository.findAll());
        }
        model.addAttribute("title", "Items");
        model.addAttribute("Area", "Item");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderItemPageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Item> o = item_repository.findById(id);

        if (o.isPresent()) {
            Item i = o.get();
            model.addAttribute("item", i);
            model.addAttribute("title", i.getName());
            model.addAttribute("Area", "Item");
            model.addAttribute("Sub_Page", "Item");
        }
        return "Master";
    }
	
	@GetMapping(value = "/{id}/Delete")
    public String ItemDeleteById(@PathVariable(name = "id", value = "id") Long id) {
		Optional<Item> optionalItem = item_repository.findById(id);
		if (optionalItem.isPresent())
			item_repository.deleteById(id);
		// TODO: check how much delete actually deletes.
        return "redirect:/Item";
	}

    @RequestMapping(value = "/{id}/Edit", method = RequestMethod.GET)
    public String RenderItemEditPageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Item> o = item_repository.findById(id);
        if (o.isPresent()) {
            ItemForm itemForm = new ItemForm(o.get());

            // Set subject area and model type
            model.addAttribute("itemForm", itemForm);
            model.addAttribute("title", itemForm.getName());
            model.addAttribute("Area", "Item");
            model.addAttribute("Sub_Page", "ItemForm");

            return CreateItem(model);
        }
        return "redirect:/Item";
    }

    @GetMapping("/Create")
    public String CreateItem(Model model) {
        model.addAttribute("title", "Edit Item");
        model.addAttribute("Area", "Item");
        model.addAttribute("Sub_Page", "ItemForm");
        model.addAttribute("modelTypes", modelType_repository.findAll());
        model.addAttribute("subjectAreaNames", subjectArea_repository.findAll());
        model.addAttribute("stores", store_repository.findAll());
        if (!model.containsAttribute("itemForm")) {
            model.addAttribute("title", "Create Item");
            model.addAttribute("itemForm", new ItemForm());
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateItem(Model model, @Valid ItemForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create Item");
        model.addAttribute("Area", "Item");
        model.addAttribute("Sub_Page", "ItemForm");

        if (bindingResult.hasErrors()) return "Master";

        Item item = new Item();

        // Are we saving an existing item?
        if(form.getId() != null) {
            Optional<Item> optionalItem = item_repository.findById(form.getId());
            if (optionalItem.isPresent()) {
                item = optionalItem.get();
            }
        }

        item.setRetailPrice(form.getRetail_price());
        item.setStock(form.getStock());
        item.setDescription(form.getDescription());
        item.setName(form.getName());
        item.setAvailability(form.getAvailability());
        item.setStore(store_repository.findById(form.getStoreId()).get());

        Optional<ModelType> optionalModelType = modelType_repository.findById(form.getModel_type());
        Optional<SubjectArea> optionalSubjectArea = subjectArea_repository.findById(form.getModel_subject_area());
        if (optionalModelType.isPresent() && optionalSubjectArea.isPresent()) {
            item.setItemModelType(optionalModelType.get());
            item.setItemSubjectArea(optionalSubjectArea.get());
        }

        item_repository.save(item);
        return String.format("redirect:/Item/%d", item.getId());
    }
}
