package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.model.Catalogue;
import com.csci334.TimsHobbyShop.model.Supplier;
import com.csci334.TimsHobbyShop.repository.Catalogue_Repository;
import com.csci334.TimsHobbyShop.repository.Supplier_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(path="/Catalogue")
public class CatalogueController {
    @Autowired
    private Catalogue_Repository catalogue_Repository;;

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
}
