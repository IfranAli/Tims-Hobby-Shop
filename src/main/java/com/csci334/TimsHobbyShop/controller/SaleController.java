package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Sale;
import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/Sale")
public class SaleController {
    @Autowired
    private Customer_Repository customerRepository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Sale_Repository sale_repository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("sales", sale_repository.findAll());
        model.addAttribute("title", "Sales");
        model.addAttribute("Area", "Sale");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderSalePageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Sale> optionalSale = sale_repository.findById(id);

        if (optionalSale.isPresent()) {
            Sale sale = optionalSale.get();
            model.addAttribute("sale", sale);
            model.addAttribute("title", "Sale");
            model.addAttribute("Area", "Sale");
            model.addAttribute("Sub_Page", "Sale");
        }
        return "Master";
    }

	@GetMapping(value = "/{id}/Delete")
    public String DeleteSaleById(@PathVariable(name = "id", value = "id") Long id) {
		Optional<Sale> optionalSale = sale_repository.findById(id);
		if (optionalSale.isPresent())
			sale_repository.deleteById(id);
		// TODO: check how much delete actually deletes.
        return "redirect:/Sale";
	}

    @RequestMapping(value = "/{id}/Edit", method = RequestMethod.GET)
    public String RenderSaleEditPageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Sale> o = sale_repository.findById(id);
        if (o.isPresent()) {

			//TODO: Populate saleForm
            SaleForm saleForm = new SaleForm(o.get());

            // Set subject area and model type
            model.addAttribute("saleForm", saleForm);
            model.addAttribute("title", saleForm.getName());
            model.addAttribute("Area", "Sale");
            model.addAttribute("Sub_Page", "SaleForm");

            return CreateSale(model);
        }
        return "redirect:/Sale";
    }

    @GetMapping("/Create")
    public String CreateSale(Model model) {
        model.addAttribute("title", "Edit Sale");
        model.addAttribute("Area", "Sale");
        model.addAttribute("Sub_Page", "SaleForm");
        if (!model.containsAttribute("saleForm")) {
            model.addAttribute("title", "Create Sale");
            model.addAttribute("saleForm", new SaleForm());
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateSalePost(Model model, @ValidSaleForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create Sale");
        model.addAttribute("Area", "Sale");
        model.addAttribute("Sub_Page", "SaleForm");

        if (bindingResult.hasErrors()) return "Master";

        Sale sale = new Sale();

        // Are we saving an existing item?
        if(form.getId() != null) {
            Optional<Sale> optionalSale = sale_repository.findById(form.getId());
            if (optionalSale.isPresent()) {
                sale = optionalSale.get();
            }
        }

		//TODO: Set sale fields from model

        sale_repository.save(sale);
        return String.format("redirect:/Sale/%d", sale.getId());
    }
}
