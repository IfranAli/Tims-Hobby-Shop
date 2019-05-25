package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.ContactPersonForm;
import com.csci334.TimsHobbyShop.model.ContactPerson;
import com.csci334.TimsHobbyShop.model.Person;
import com.csci334.TimsHobbyShop.repository.ContactPerson_Repository;
import com.csci334.TimsHobbyShop.repository.Person_Repository;
import com.csci334.TimsHobbyShop.repository.Supplier_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path="/ContactPerson")
public class ContactPersonController {
    @Autowired
    private Person_Repository personRepository;
    @Autowired
    private ContactPerson_Repository contactPerson_repository;
    @Autowired
    private Supplier_Repository supplier_repository;

    /* Returns all ContactPersons in the database */
    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        model.addAttribute("contactPersons", contactPerson_repository.findAll());
        model.addAttribute("title", "ContactPerson");
        model.addAttribute("Area", "ContactPerson");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderContactPersonPageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<ContactPerson> optionalItem = contactPerson_repository.findById(id);

        if (optionalItem.isPresent()) {
            ContactPerson c = optionalItem.get();
            model.addAttribute("contactPerson", c);
            model.addAttribute("title", c.getPerson().getName());
            model.addAttribute("Area", "ContactPerson");
            model.addAttribute("Sub_Page", "Profile");
        }
        return "Master";
    }

    @RequestMapping(value = "/{cid}/Delete", method = RequestMethod.GET)
    public String DeleteContactPerson(@PathVariable(name = "cid", value = "cid") Long id, Model model) {
        Optional<ContactPerson> optionalContactPerson = contactPerson_repository.findById(id);
        if (optionalContactPerson.isPresent()) {
            contactPerson_repository.delete(optionalContactPerson.get());
        }
        return "redirect:/ContactPerson";
    }

    @RequestMapping(value = "/{cid}/Edit", method = RequestMethod.GET)
    public String EditContactPerson(@PathVariable(name = "cid", value = "cid") Long ic, Model model) {
        Optional<ContactPerson> optionalContactPerson = contactPerson_repository.findById(ic);
        if (optionalContactPerson.isPresent()) {
            ContactPersonForm contactPersonForm = new ContactPersonForm(optionalContactPerson.get());
            model.addAttribute("contactPersonForm", contactPersonForm);
            return CreateContactPerson(model);
        }
        return "redirect:/ContactPerson";
    }

    @GetMapping(path="/Create")
    public String CreateContactPerson(Model model) {
        model.addAttribute("title", "Edit ContactPerson");
        model.addAttribute("Area", "ContactPerson");
        model.addAttribute("Sub_Page", "ContactPersonForm");

        model.addAttribute("suppliers", supplier_repository.findAll());

        if (!model.containsAttribute("contactPersonForm")) {
			ContactPersonForm contactPersonForm = new ContactPersonForm();

            model.addAttribute("title", "Create ContactPerson");
            model.addAttribute("contactPersonForm", contactPersonForm);
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateContactPerson(Model model, @Valid ContactPersonForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create ContactPerson");
        model.addAttribute("Area", "ContactPerson");
        model.addAttribute("Sub_Page", "ContactPersonForm");

        if (bindingResult.hasErrors()) return CreateContactPerson(model);

        // Save in database.
        Person person = new Person();
        ContactPerson contactPerson = new ContactPerson();

        // Are we saving an existing ContactPerson?
        if(form.getId() != null) {
            Optional<ContactPerson> optionalContactPerson = contactPerson_repository.findById(form.getId());
            if (optionalContactPerson.isPresent()) {
                contactPerson = optionalContactPerson.get();

                // Do they have an associated person entity?
                Optional<Person> optionalPerson = personRepository.findById(form.getPersonId());
                if (optionalPerson.isPresent()) {
                    person = optionalPerson.get();
                }
            }
        }
        // Update fields.
        person.setName(form.getName());
        person.setEmail(form.getEmail());
        person.setPhone(form.getPhone());
        person.setRole(form.getRole());
        personRepository.save(person);

        contactPerson.setPerson(person);
        contactPerson.setSupplier(supplier_repository.findById(form.getSupplerId()).get());
        contactPerson_repository.save(contactPerson);

        return "redirect:/ContactPerson/";
    }
}
