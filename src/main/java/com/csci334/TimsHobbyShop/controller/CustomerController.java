package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.CustomerForm;
import com.csci334.TimsHobbyShop.DTO.CustomerModelInterestDTO;
import com.csci334.TimsHobbyShop.DTO.CustomerSubjectInterestDTO;
import com.csci334.TimsHobbyShop.model.*;
import com.csci334.TimsHobbyShop.repository.*;
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
@RequestMapping(path="/Customer")
public class CustomerController {
    @Autowired
    private Customer_Repository customerRepository;
    @Autowired
    private Person_Repository personRepository;
    @Autowired
    private Item_Repository item_repository;
    @Autowired
    private Sale_Repository sale_repository;
    @Autowired
    private ModelType_Repository modelType_repository;
    @Autowired
    private SubjectArea_Repository subjectArea_repository;
    @Autowired
    private CustomerSubjectInterest_Repository customerSubjectInterest_repository;
    @Autowired
    private CustomerModelInterest_Repository customerModelInterest_repository;
    @Autowired
    private ClubMember_Repository clubMember_repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderCustomerPageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Customer> optionalItem = customerRepository.findById(id);

        if (optionalItem.isPresent()) {
            Customer c = optionalItem.get();
            model.addAttribute("sales", sale_repository.findSalesByCustomerId(c.getId()));
            model.addAttribute("customer", c);
            model.addAttribute("title", c.getPerson().getName());
            model.addAttribute("Area", "Customer");
            model.addAttribute("Sub_Page", "Profile");
        }
        return "Master";
    }

    /* Returns all customers in the database */
    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        if(query != null && !query.isEmpty()) {
            model.addAttribute("customers", customerRepository.searchByName(query));
            model.addAttribute("query", query);
        } else {
            model.addAttribute("customers", customerRepository.findAll());
        }
        model.addAttribute("title", "Customers");
        model.addAttribute("Area", "Customer");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }

    @RequestMapping(value = "/{cid}/Delete", method = RequestMethod.GET)
    public String DeleteCustomer(@PathVariable(name = "cid", value = "cid") Long customerID, Model model) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            ClubMember clubMember = customer.getClubMembership();
            if (clubMember != null) {
                clubMember_repository.delete(clubMember);
            }
            customerRepository.delete(customer);
        }
        return "redirect:/Customer";
    }

    @RequestMapping(value = "/{cid}/Edit", method = RequestMethod.GET)
    public String EditCustomer(@PathVariable(name = "cid", value = "cid") Long customerID, Model model) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            CustomerForm customerForm = new CustomerForm();
			customerForm.FromEntity(customer);

			// Gather Customer model type interests
            List<CustomerModelInterestDTO> customerModelInterestDTOS = new ArrayList<>();
            customerModelInterest_repository.getCustomerModelInterestById(customer.getId()).forEach(i -> {
                customerModelInterestDTOS.add(new CustomerModelInterestDTO(i));
            });

            // Gather Customer Subject Area interests
            List<CustomerSubjectInterestDTO> customerSubjectInterestDTOS = new ArrayList<>();
            customerSubjectInterest_repository.getCustomerSubjectInterestById(customer.getId()).forEach( i -> {
                customerSubjectInterestDTOS.add(new CustomerSubjectInterestDTO(i));
            });

            // Append Subject and Area Interests to the model.
            customerForm.setModelNames(customerModelInterestDTOS);
            customerForm.setSubjectAreaNames(customerSubjectInterestDTOS);

            model.addAttribute("customerForm", customerForm);
            return CreateCustomer(model);
        }
        return "redirect:/Customer";
    }

    @GetMapping(path="/Create")
    public String CreateCustomer(Model model) {
        model.addAttribute("title", "Edit Customer");
        model.addAttribute("Area", "Customer");
        model.addAttribute("Sub_Page", "CustomerForm");

        if (!model.containsAttribute("customerForm")) {
			CustomerForm customerForm = new CustomerForm();

            // Gather Customer model type interests
            List<CustomerModelInterestDTO> customerModelInterestDTOS = new ArrayList<>();
            customerModelInterest_repository.getCustomerModelInterestById(-1L).forEach(i -> {
                customerModelInterestDTOS.add(new CustomerModelInterestDTO(i));
            });

            // Gather Customer Subject Area interests
            List<CustomerSubjectInterestDTO> customerSubjectInterestDTOS = new ArrayList<>();
            customerSubjectInterest_repository.getCustomerSubjectInterestById(-1L).forEach( i -> {
                customerSubjectInterestDTOS.add(new CustomerSubjectInterestDTO(i));
            });

            // Append Subject and Area Interests to the model.
            customerForm.setModelNames(customerModelInterestDTOS);
            customerForm.setSubjectAreaNames(customerSubjectInterestDTOS);

            model.addAttribute("title", "Create Customer");
            model.addAttribute("customerForm", customerForm);
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateCustomer(Model model, @Valid CustomerForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create Customer");
        model.addAttribute("Area", "Customer");
        model.addAttribute("Sub_Page", "CustomerForm");

        if (bindingResult.hasErrors()) return CreateCustomer(model);

        // Save in database.
        Person person = new Person();
        Customer customer = new Customer();

        // Are we saving an existing customer?
        if(form.getCustomerID() != null) {
            Optional<Customer> optionalCustomer = customerRepository.findById(form.getCustomerID());
            if (optionalCustomer.isPresent()) {
                customer = optionalCustomer.get();
                Optional<Person> optionalPerson = personRepository.findById(customer.getPerson().getId());
                if (optionalPerson.isPresent()) {
                    person = optionalPerson.get();
                }
            }
        } else {
            // Safety precaution incase a club member is defined
            // in the customer create form.
            customerRepository.save(customer);
        }

        person.setName(form.getName());
//        person.setUsername(form.getUsername());
//        person.setPassword(form.getPassword());
        person.setEmail(form.getEmail());
        person.setPhone(form.getPhone());
        personRepository.save(person);

        customer.setAddress(form.getAddress());
        customer.setBalance(form.getBalance());
        customer.setCreditline(form.getCreditline());
        customer.setPerson(person);

        // Update club member status.
        ClubMember clubMember = customer.getClubMembership();
        if (clubMember != null && form.getClubMember()) {
            clubMember.setJoinDate(form.getClubMemeberJoinDate());
        } else if (!form.getClubMember()) {
            clubMember_repository.delete(clubMember);
            customer.setClubMembership(null);
        } else {
            clubMember = new ClubMember();
            clubMember.setMember(customer);
            clubMember.setJoinDate(form.getClubMemeberJoinDate());
            customer.setClubMembership(clubMember);
            clubMember_repository.save(clubMember);
        }

        // Update customer interests
        // Deletes old interests and adds new ones.
        customerModelInterest_repository.deleteByCustomerID(customer.getId());
        customerSubjectInterest_repository.deleteByCustomerID(customer.getId());
        ArrayList<CustomerModelInterest> customerModelInterests = new ArrayList<>();
        ArrayList<CustomerSubjectInterest> customerSubjectInterests = new ArrayList<>();

        for (CustomerModelInterestDTO dto : form.getModelNames()) {
            if (dto.getActive()) {
                CustomerModelInterest modelInterest = new CustomerModelInterest();
                modelInterest.setCustomerWithModelInterest(customer);
                modelInterest.setModelType(modelType_repository.findById(dto.getModelTypeID()).get());
                customerModelInterests.add(modelInterest);
            }
        }
        for (CustomerSubjectInterestDTO dto : form.getSubjectAreaNames()) {
            if (dto.getActive()) {
                CustomerSubjectInterest subjectInterest = new CustomerSubjectInterest();
                subjectInterest.setCustomerWithSubjectInterest(customer);
                subjectInterest.setSubjectArea(subjectArea_repository.findById(dto.getSubjectAreaID()).get());
                customerSubjectInterests.add(subjectInterest);
            }
        }

        customer.setModelTypeInterests(customerModelInterests);
        customer.setSubjectAreaInterests(customerSubjectInterests);
        customerRepository.save(customer);

        return "redirect:/Customer/" + customer.getId();
    }
}
