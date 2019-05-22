package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.ItemDTO;
import com.csci334.TimsHobbyShop.DTO.SaleForm;
import com.csci334.TimsHobbyShop.DTO.SaleLineItemDTO;
import com.csci334.TimsHobbyShop.model.Sale;
import com.csci334.TimsHobbyShop.model.SaleLineItem;
import com.csci334.TimsHobbyShop.repository.Customer_Repository;
import com.csci334.TimsHobbyShop.repository.Item_Repository;
import com.csci334.TimsHobbyShop.repository.SaleLineItem_Repository;
import com.csci334.TimsHobbyShop.repository.Sale_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path="/Sale")
public class SaleController {
    @Autowired
    private Customer_Repository customerRepository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Sale_Repository sale_repository;
    @Autowired
    private SaleLineItem_Repository saleLineItem_repository;

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
            model.addAttribute("title", "Sale " + sale.getSaleDate());
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
        Optional<Sale> optionalSale = sale_repository.findById(id);
        if (optionalSale.isPresent()) {
            Sale sale = optionalSale.get();
            SaleForm saleForm = new SaleForm(sale);

            // Get SaleLineItems for this sale, convert, and add to form model as DTO.
            ArrayList<SaleLineItemDTO> saleLineItemDTOs = new ArrayList<>();
            saleLineItem_repository.findBySaleId(sale.getId()).forEach(i -> saleLineItemDTOs.add(new SaleLineItemDTO(i)));
            saleForm.setSaleLineItemDTOs(saleLineItemDTOs);

            // Set subject area and model type
            model.addAttribute("saleForm", saleForm);
            model.addAttribute("title", saleForm.getSale_date());
            model.addAttribute("Area", "Sale");
            model.addAttribute("Sub_Page", "SaleForm");

            return CreateSale(model);
        }
        return "redirect:/Sale";
    }

    @RequestMapping(value = "/Create/{id}", method = RequestMethod.GET)
    public String RenderSaleCreatePageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        SaleForm saleForm = new SaleForm();
        saleForm.setCustomerId(id);
        model.addAttribute("saleForm", saleForm);
        return CreateSale(model);
    }

    @GetMapping("/Create")
    public String CreateSale(Model model) {
        model.addAttribute("title", "Edit Sale");
        model.addAttribute("Area", "Sale");
        model.addAttribute("Sub_Page", "SaleForm");

        ArrayList<ItemDTO> itemDTOs = new ArrayList<>();
        item_repository.findAll().forEach(i -> itemDTOs.add(new ItemDTO(i)));
        model.addAttribute("items", itemDTOs);
        model.addAttribute("customers", customerRepository.findAll());

        if (!model.containsAttribute("saleForm")) {
            model.addAttribute("title", "Create Sale");
            model.addAttribute("saleForm", new SaleForm());
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateSalePost(Model model, @Valid SaleForm form, BindingResult bindingResult) {
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

        //sale.setTotal(form.getTotal());
        sale.setDiscount(form.getDiscount());
        sale.setStatus(form.getStatus());
        sale.setSaleDate(form.getSale_date());
        if (form.getCustomerId() != null) {
            sale.setCustomer(customerRepository.findById(form.getCustomerId()).get());
        } else {
            sale.setCustomer(null);
        }

        ArrayList<SaleLineItem> saleLineItems = new ArrayList<>();
        saleLineItem_repository.deleteAllBySaleID(form.getId());
        for (SaleLineItemDTO dto : form.getSaleLineItemDTOs()) {
            SaleLineItem saleLineItem = new SaleLineItem();
            saleLineItem.setItem(item_repository.findById(dto.getItemId()).get());
            saleLineItem.setSale(sale);
            saleLineItem.setQuantity(dto.getQuantity());
            saleLineItem.setSale_price(dto.getSale_price());
            //saleLineItem_repository.save(saleLineItem);
            saleLineItems.add(saleLineItem);
        }
        sale.setSale_line_items(saleLineItems);
        sale.updateTotal();
        sale_repository.save(sale);
        return String.format("redirect:/Sale/%d", sale.getId());
    }
}
