package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.EmployeeForm;
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
import java.util.*;

@Controller
@RequestMapping(path="/Employee")
public class EmployeeController {
    @Autowired
    private Person_Repository personRepository;

    /* Returns all employees in the database */
    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        if(query != null && !query.isEmpty()) {
//            model.addAttribute("employees", personRepository.searchByName(query));
            model.addAttribute("employees", personRepository.GetAllEmployees());
            model.addAttribute("query", query);
        } else {
            model.addAttribute("employees", personRepository.GetAllEmployees());
        }
        model.addAttribute("title", "Employees");
        model.addAttribute("Area", "Employee");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String RenderEmployeePageById(@PathVariable(name = "id", value = "id") Long id, Model model) {
        Optional<Person> optionalItem = personRepository.findById(id);

        if (optionalItem.isPresent()) {
            Person c = optionalItem.get();
            model.addAttribute("employee", c);
            model.addAttribute("title", c.getName());
            model.addAttribute("Area", "Employee");
            model.addAttribute("Sub_Page", "Profile");
        }
        return "Master";
    }

    @RequestMapping(value = "/{cid}/Delete", method = RequestMethod.GET)
    public String DeleteEmployee(@PathVariable(name = "cid", value = "cid") Long employeeID, Model model) {
        Optional<Person> optionalEmployee = personRepository.findById(employeeID);
        if (optionalEmployee.isPresent()) {
            personRepository.delete(optionalEmployee.get());
        }
        return "redirect:/Employee";
    }

    @RequestMapping(value = "/{cid}/Edit", method = RequestMethod.GET)
    public String EditEmployee(@PathVariable(name = "cid", value = "cid") Long employeeID, Model model) {
        Optional<Person> optionalEmployee = personRepository.findById(employeeID);
        if (optionalEmployee.isPresent()) {
            Person employee = optionalEmployee.get();
            EmployeeForm employeeForm = new EmployeeForm();
			employeeForm.FromEntity(employee);

            model.addAttribute("employeeForm", employeeForm);
            return CreateEmployee(model);
        }
        return "redirect:/Employee";
    }

    @GetMapping(path="/Create")
    public String CreateEmployee(Model model) {
        model.addAttribute("title", "Edit Employee");
        model.addAttribute("Area", "Employee");
        model.addAttribute("Sub_Page", "EmployeeForm");

        if (!model.containsAttribute("employeeForm")) {
			EmployeeForm employeeForm = new EmployeeForm();

            model.addAttribute("title", "Create Employee");
            model.addAttribute("employeeForm", employeeForm);
        }
        return "Master";
    }

    @PostMapping("/Create")
    public String CreateEmployee(Model model, @Valid EmployeeForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create Employee");
        model.addAttribute("Area", "Employee");
        model.addAttribute("Sub_Page", "EmployeeForm");

        if (bindingResult.hasErrors()) return CreateEmployee(model);

        // Check password and confirm password match
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            bindingResult.addError(new FieldError("employeeForm", "passwordConfirm",
                    "Password and ConfirmPassword must match"));
        }

        // Check if username already exists in the database
        if (form.getEmployeeID() == null && personRepository.findByUsername(form.getUsername()) != null) {
            bindingResult.addError(new FieldError("employeeForm", "username",
                    "Username exists"));
        }

        if (bindingResult.hasErrors()) return CreateEmployee(model);

        // Save in database.
        Person person = new Person();

        // Are we saving an existing employee?
        if(form.getEmployeeID() != null) {
            Optional<Person> optionalEmployee = personRepository.findById(form.getEmployeeID());
            if (optionalEmployee.isPresent()) {
                person = optionalEmployee.get();
            }
        }

        person.setName(form.getName());
        person.setUsername(form.getUsername());
        person.setPassword(form.getPassword());
        person.setEmail(form.getEmail());
        person.setPhone(form.getPhone());
        person.setAddress(form.getAddress());
        person.setRole(form.getRole());
        personRepository.save(person);

        return "redirect:/Employee/" + person.getId();
    }
}
