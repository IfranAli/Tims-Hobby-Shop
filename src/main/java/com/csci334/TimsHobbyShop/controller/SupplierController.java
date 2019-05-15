package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.model.Supplier;
import com.csci334.TimsHobbyShop.repository.Catalogue_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.Supplier_Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(path="/Supplier")
public class SupplierController {
    @Autowired
    private Supplier_Repository supplier_Repository;;
    @Autowired
    private Catalogue_Repository catalogue_Repository;;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        model.addAttribute("title", "Suppliers");
        model.addAttribute("Area", "Supplier");
        model.addAttribute("Sub_Page", "Index");

        if(query != null && !query.isEmpty()) {
            model.addAttribute("suppliers", supplier_Repository.searchByCompanyName(query));
            model.addAttribute("query", query);
        } else {
			model.addAttribute("suppliers", supplier_Repository.findAll());
		}
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderSupplierPageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Supplier> optionalSupplier = supplier_Repository.findById(id);

        if (optionalSupplier.isPresent()) {
			Supplier supplier = optionalSupplier.get();
            model.addAttribute("catalogues", catalogue_Repository.findById(supplier.getId()));
            model.addAttribute("supplier", supplier);
            model.addAttribute("title", supplier.getCompanyName());
            model.addAttribute("Area", "Supplier");
            model.addAttribute("Sub_Page", "Profile");
        }
        return "Master";
    }
}
