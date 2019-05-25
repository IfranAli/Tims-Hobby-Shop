package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.DeliveryForm;
import com.csci334.TimsHobbyShop.DTO.DeliveryItemDTO;
import com.csci334.TimsHobbyShop.DTO.ItemDTO;
import com.csci334.TimsHobbyShop.DTO.SupplierItemDTO;
import com.csci334.TimsHobbyShop.model.*;
import com.csci334.TimsHobbyShop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/Delivery")
public class DeliveryController {
    @Autowired
    private Delivery_Repository delivery_Repository;
    @Autowired
    private DeliveryItem_Repository deliveryItem_repository;
    @Autowired
    private Store_Repository store_repository;
    @Autowired
    private Supplier_Repository supplier_repository;
    @Autowired
    private ContactPerson_Repository contactPerson_repository;
    @Autowired
    private SupplierItem_Repository supplierItem_repository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Catalogue_Repository catalogue_repository;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        model.addAttribute("title", "Deliveries");
        model.addAttribute("Area", "Delivery");
        model.addAttribute("Sub_Page", "Index");

        model.addAttribute("deliveries", delivery_Repository.findAll());
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderDeliveryPageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Delivery> optionalDelivery = delivery_Repository.findById(id);

        if (optionalDelivery.isPresent()) {
			Delivery delivery = optionalDelivery.get();
            model.addAttribute("delivery", delivery);
            model.addAttribute("title", "Delivery " + delivery.getDeliveryDate());
            model.addAttribute("Area", "Delivery");
            model.addAttribute("Sub_Page", "Profile");
        }
        return "Master";
    }

    @RequestMapping(value = "/{cid}/Delete", method = RequestMethod.GET)
    public String DeleteDelivery(@PathVariable(name = "cid", value = "cid") Long deliveryID, Model model) {
        Optional<Delivery> optional = delivery_Repository.findById(deliveryID);
        if (optional.isPresent()) {
            delivery_Repository.delete(optional.get());
        }
        return "redirect:/Delivery";
    }

    @RequestMapping(value = "/{cid}/Edit", method = RequestMethod.GET)
    public String EditDelivery(@PathVariable(name = "cid", value = "cid") Long deliveryID, Model model) {
        Optional<Delivery> optionalDelivery= delivery_Repository.findById(deliveryID);
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            DeliveryForm deliveryForm = new DeliveryForm(delivery);

            model.addAttribute("deliveryForm", deliveryForm);
            return CreateDelivery(model, deliveryForm);
        }
        return "redirect:/Delivery";
    }

    @GetMapping(path="/CreateSetup")
    public String SetupDelivery(Model model) {
        model.addAttribute("title", "Edit Delivery");
        model.addAttribute("Area", "Delivery");
        model.addAttribute("Sub_Page", "DeliverySetupForm");

        model.addAttribute("stores", store_repository.findAll());
        model.addAttribute("suppliers", supplier_repository.findAll());

        if (!model.containsAttribute("deliveryForm")) {
            DeliveryForm deliveryForm = new DeliveryForm();
            model.addAttribute("title", "Create Delivery");
            model.addAttribute("deliveryForm", deliveryForm);
        }
        return "Master";
    }
    @PostMapping(path="/CreateSetup")
    public String SetupDelivery(Model model, @Valid DeliveryForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return SetupDelivery(model);
        return CreateDelivery(model, form);
    }

    @GetMapping(path="/Create")
    public String CreateDelivery(Model model, @Valid DeliveryForm form) {
        model.addAttribute("title", "Edit Delivery");
        model.addAttribute("Area", "Delivery");
        model.addAttribute("Sub_Page", "DeliveryForm");

        model.addAttribute("stores", store_repository.findAll());
        model.addAttribute("suppliers", supplier_repository.findAll());

        ArrayList<SupplierItemDTO> supplierItemDTOS = new ArrayList<>();
        for (Catalogue c : catalogue_repository.findBySupplierId(form.getSupplierId())) {
            c.getSupplierItems().forEach(supplierItem -> supplierItemDTOS.add(new SupplierItemDTO(supplierItem)));
        }
        model.addAttribute("supplieritems", supplierItemDTOS);

        if (!model.containsAttribute("deliveryForm")) {
            DeliveryForm deliveryForm = new DeliveryForm();
            model.addAttribute("title", "Create Delivery");
            model.addAttribute("deliveryForm", deliveryForm);
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateDelivery(Model model, @Valid DeliveryForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create Delivery");
        model.addAttribute("Area", "Delivery");
        model.addAttribute("Sub_Page", "DeliveryForm");

        if (bindingResult.hasErrors()) return CreateDelivery(model, form);
        if (form.getDeliveryItems() == null || form.getDeliveryItems().isEmpty()) {
            bindingResult.addError(new FieldError("DeliveryForm", "supplierId",
                    "Delivery must contain at least one item"));
        }
        if (bindingResult.hasErrors()) return CreateDelivery(model, form);

        // Save in database.
        Delivery delivery = new Delivery();

        // Are we saving an existing delivery?
        if(form.getId() != null) {
            Optional<Delivery> optional = delivery_Repository.findById(form.getId());
            if (optional.isPresent()) {
                delivery = optional.get();
            }
        }

        delivery.setStore(store_repository.findById(form.getStoreId()).get());
        delivery.setSupplier(supplier_repository.findById(form.getSupplierId()).get());
        delivery.setContactPerson(contactPerson_repository.findById(form.getContactPersonId()).get());
        delivery.setStatus(form.getStatus());
        delivery_Repository.save(delivery); // Contingency

        List<DeliveryItem> deliveryItemList_old = delivery.getDeliveryItems();

        List<DeliveryItem> deliveryItemList_new = new ArrayList<>();
        for (DeliveryItemDTO deliveryItemDTO : form.getDeliveryItems()) {
            DeliveryItem i = new DeliveryItem();
            if (deliveryItemDTO.getId() != null) {
                i = deliveryItem_repository.findById(deliveryItemDTO.getId()).get();
                i.setPrice(deliveryItemDTO.getPrice());
                i.setQuantity(deliveryItemDTO.getQuantity());
                i.setSupplierItem(supplierItem_repository.findById(deliveryItemDTO.getSupplierItemId()).get());
            } else {
                i.setSupplierItem(supplierItem_repository.findById(deliveryItemDTO.getSupplierItemId()).get());
                i.setDelivery(delivery);
                i.setPrice(deliveryItemDTO.getPrice());
                i.setQuantity(deliveryItemDTO.getQuantity());
            }
            deliveryItem_repository.save(i);
            deliveryItemList_new.add(i);
        }
        if (deliveryItemList_old != null && !deliveryItemList_old.isEmpty()) {
            deliveryItemList_old.removeAll(deliveryItemList_new);
            deliveryItemList_old.forEach(d -> deliveryItem_repository.delete(d));
        }
        delivery.setDeliveryItems(deliveryItemList_new);
        delivery.updateTotal();
        delivery_Repository.save(delivery);

        return "redirect:/Delivery/" + delivery.getId();
    }
}
