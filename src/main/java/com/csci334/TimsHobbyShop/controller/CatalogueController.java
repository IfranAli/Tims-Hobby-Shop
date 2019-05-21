package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.CatalogueForm;
import com.csci334.TimsHobbyShop.DTO.ItemDTO;
import com.csci334.TimsHobbyShop.DTO.SupplierItemDTO;
import com.csci334.TimsHobbyShop.model.Catalogue;
import com.csci334.TimsHobbyShop.model.Supplier;
import com.csci334.TimsHobbyShop.model.SupplierItem;
import com.csci334.TimsHobbyShop.repository.Catalogue_Repository;
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
@RequestMapping(path="/Catalogue")
public class CatalogueController {
    @Autowired
    private Catalogue_Repository catalogue_Repository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private SupplierItem_Repository supplierItem_repository;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        model.addAttribute("title", "Catalogues");
        model.addAttribute("Area", "Catalogue");
        model.addAttribute("Sub_Page", "Index");

        if(query != null && !query.isEmpty()) {
            model.addAttribute("catalogues", catalogue_Repository.findAll());
            model.addAttribute("query", query);
        } else {
			model.addAttribute("catalogues", catalogue_Repository.findAll());
		}
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderCataloguePageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Catalogue> optionalCatalogue = catalogue_Repository.findById(id);

        if (optionalCatalogue.isPresent()) {
            Catalogue catalogue = optionalCatalogue.get();
            String pageTitle = String.format("%s's %s Catalogue", catalogue.getSupplier().getCompanyName(), catalogue.getDate_publish());
            model.addAttribute("catalogue", catalogue);
            model.addAttribute("title", pageTitle);
            model.addAttribute("Area", "Catalogue");
            model.addAttribute("Sub_Page", "Catalogue");
        }
        return "Master";
    }

    @RequestMapping(value = "/{cid}/Delete", method = RequestMethod.GET)
    public String DeleteCatalogue(@PathVariable(name = "cid", value = "cid") Long catalogueID, Model model) {
        Optional<Catalogue> optionalCatalogue = catalogue_Repository.findById(catalogueID);
        if (optionalCatalogue.isPresent()) {
            catalogue_Repository.delete(optionalCatalogue.get());
        }
        return "redirect:/Catalogue";
    }

    @RequestMapping(value = "/{cid}/Edit", method = RequestMethod.GET)
    public String EditCatalogue(@PathVariable(name = "cid", value = "cid") Long catalogueID, Model model) {
        Optional<Catalogue> optionalCatalogue= catalogue_Repository.findById(catalogueID);
        if (optionalCatalogue.isPresent()) {
            model.addAttribute("catalogueForm", new CatalogueForm(optionalCatalogue.get()));
            return CreateCatalogue(model);
        }
        return "redirect:/Catalogue";
    }

    @GetMapping(path="/Create")
    public String CreateCatalogue(Model model) {
        model.addAttribute("title", "Edit Catalogue");
        model.addAttribute("Area", "Catalogue");
        model.addAttribute("Sub_Page", "CatalogueForm");

        ArrayList<ItemDTO> itemDTOs = new ArrayList<>();
        item_repository.findAll().forEach(i -> itemDTOs.add(new ItemDTO(i)));
        model.addAttribute("items", itemDTOs);

        if (!model.containsAttribute("catalogueForm")) {
            CatalogueForm catalogueForm = new CatalogueForm();
            model.addAttribute("title", "Create Catalogue");
            model.addAttribute("catalogueForm", catalogueForm);
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateCatalogue(Model model, @Valid CatalogueForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create Catalogue");
        model.addAttribute("Area", "Catalogue");
        model.addAttribute("Sub_Page", "CatalogueForm");

        if (bindingResult.hasErrors()) return CreateCatalogue(model);

        // Save in database.
        Catalogue catalogue = new Catalogue();

        // Are we saving an existing supplier?
        if(form.getId() != null) {
            Optional<Catalogue> optional = catalogue_Repository.findById(form.getId());
            if (optional.isPresent()) {
                catalogue = optional.get();
            }
        }

        catalogue.setDate_publish(form.getDate_publish());
        List<SupplierItem> supplierItems_old = catalogue.getSupplierItems();
        List<SupplierItem> supplierItems_new = new ArrayList<>();
        for (SupplierItemDTO supplierItemDTO : form.getSupplierItems()) {
            SupplierItem i = new SupplierItem();
            if (supplierItemDTO.getSupplierItemId() != null) {
                i = supplierItem_repository.findById(supplierItemDTO.getSupplierItemId()).get();
                i.setPrice(supplierItemDTO.getSupplier_price());
                i.setCatalogue(catalogue);
                i.setSuppliedItem(item_repository.findById(supplierItemDTO.getItemId()).get());
            } else {
                i.setSuppliedItem(item_repository.findById(supplierItemDTO.getItemId()).get());
                i.setCatalogue(catalogue);
                i.setPrice(supplierItemDTO.getSupplier_price());
            }
            supplierItem_repository.save(i);
            supplierItems_new.add(i);
        }
        supplierItems_old.removeAll(supplierItems_new);
        supplierItems_old.forEach(supplierItem -> supplierItem_repository.delete(supplierItem));
        catalogue.setSupplierItems(supplierItems_new);
        catalogue_Repository.save(catalogue);

        return "redirect:/Catalogue/" + catalogue.getId();
    }
}
