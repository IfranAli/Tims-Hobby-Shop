package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.SupplierForm;
import com.csci334.TimsHobbyShop.model.Supplier;
import com.csci334.TimsHobbyShop.repository.Catalogue_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.Supplier_Repository;
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
            model.addAttribute("supplier", supplier);
            model.addAttribute("title", supplier.getCompanyName());
            model.addAttribute("Area", "Supplier");
            model.addAttribute("Sub_Page", "Profile");
        }
        return "Master";
    }

    @RequestMapping(value = "/{cid}/Delete", method = RequestMethod.GET)
    public String DeleteSupplier(@PathVariable(name = "cid", value = "cid") Long supplierID, Model model) {
        Optional<Supplier> optional = supplier_Repository.findById(supplierID);
        if (optional.isPresent()) {
            supplier_Repository.delete(optional.get());
        }
        return "redirect:/Supplier";
    }

    @RequestMapping(value = "/{cid}/Edit", method = RequestMethod.GET)
    public String EditSupplier(@PathVariable(name = "cid", value = "cid") Long supplierID, Model model) {
        Optional<Supplier> optionalSupplier= supplier_Repository.findById(supplierID);
        if (optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();
            SupplierForm supplierForm = new SupplierForm(supplier);

            model.addAttribute("supplierForm", supplierForm);
            return CreateSupplier(model);
        }
        return "redirect:/Customer";
    }

    @GetMapping(path="/Create")
    public String CreateSupplier(Model model) {
        model.addAttribute("title", "Edit Supplier");
        model.addAttribute("Area", "Supplier");
        model.addAttribute("Sub_Page", "SupplierForm");

        if (!model.containsAttribute("supplierForm")) {
            SupplierForm supplierForm = new SupplierForm();

            model.addAttribute("title", "Create Supplier");
            model.addAttribute("supplierForm", supplierForm);
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateCustomer(Model model, @Valid SupplierForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create Supplier");
        model.addAttribute("Area", "Supplier");
        model.addAttribute("Sub_Page", "SupplierForm");

        if (bindingResult.hasErrors()) return CreateSupplier(model);

        // Save in database.
        Supplier supplier = new Supplier();

        // Are we saving an existing supplier?
        if(form.getId() != null) {
            Optional<Supplier> optional = supplier_Repository.findById(form.getId());
            if (optional.isPresent()) {
                supplier = optional.get();
            }
        }

        supplier.setAddress(form.getAddress());
        supplier.setCompanyName(form.getCompanyName());
        supplier.setCreditline(form.getCreditline());

        supplier_Repository.save(supplier);

        return "redirect:/Supplier/" + supplier.getId();
    }
}
