package com.csci334.TimsHobbyShop.controller;

import com.csci334.TimsHobbyShop.DTO.ModelTypeForm;
import com.csci334.TimsHobbyShop.DTO.SubjectAreaForm;
import com.csci334.TimsHobbyShop.model.ModelType;
import com.csci334.TimsHobbyShop.model.SubjectArea;
import com.csci334.TimsHobbyShop.repository.CustomerModelInterest_Repository;
import com.csci334.TimsHobbyShop.repository.CustomerSubjectInterest_Repository;
import com.csci334.TimsHobbyShop.repository.ModelType_Repository;
import com.csci334.TimsHobbyShop.repository.SubjectArea_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path="/ItemType")
public class ItemTypeController {
    @Autowired
    private ModelType_Repository modelType_repository;
    @Autowired
    private SubjectArea_Repository subjectArea_repository;
    @Autowired
    private CustomerSubjectInterest_Repository customerSubjectInterest_repository;
    @Autowired
    private CustomerModelInterest_Repository customerModelInterest_repository;

    /* Returns all Subject Areas and Model Types in the database */
    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@RequestParam(value = "query", required = false) String query, Model model) {
        model.addAttribute("modelTypes", modelType_repository.findAll());
        model.addAttribute("subjectAreas", subjectArea_repository.findAll());
        model.addAttribute("title", "ItemTypes");
        model.addAttribute("Area", "ItemTypes");
        model.addAttribute("Sub_Page", "Index");
        return "Master";
    }


    @RequestMapping(value = "/ModelType/{cid}/Delete", method = RequestMethod.GET)
    public String DeleteItemTypes(@PathVariable(name = "cid", value = "cid") Long id, Model model) {
        Optional<ModelType> o = modelType_repository.findById(id);
        if (o.isPresent()) {
            customerModelInterest_repository.deleteCustomerModelInterestByModelInterestID(id);
            modelType_repository.delete(o.get());
        }
        return "redirect:/ItemType";
    }

    @RequestMapping(value = "/ModelType/{cid}/Edit", method = RequestMethod.GET)
    public String EditItemTypes(@PathVariable(name = "cid", value = "cid") Long ic, Model model) {
        Optional<ModelType> o = modelType_repository.findById(ic);
        if (o.isPresent()) {
            ModelTypeForm modelTypeForm = new ModelTypeForm(o.get());
            model.addAttribute("modelTypeForm", modelTypeForm);
            return CreateModelType(model);
        }
        return "redirect:/ItemType";
    }

    @GetMapping(path="/ModelType/Create")
    public String CreateModelType(Model model) {
        model.addAttribute("title", "Edit ModelType");
        model.addAttribute("Area", "ItemTypes");
        model.addAttribute("Sub_Page", "ModelTypeForm");

        if (!model.containsAttribute("modelTypeForm")) {
            ModelTypeForm modelTypeForm = new ModelTypeForm();

            model.addAttribute("title", "Create ModelType");
            model.addAttribute("modelTypeForm", modelTypeForm);
        }
        return "Master";
    }

    @PostMapping("/ModelType/Create")
    public String CreateModelType(Model model, @Valid ModelTypeForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create ModelType");
        model.addAttribute("Area", "ItemTypes");
        model.addAttribute("Sub_Page", "ModelTypeForm");

        if (bindingResult.hasErrors()) return CreateModelType(model);

        // Save in database.
        ModelType modelType = new ModelType();

        // Are we saving an existing ItemTypes?
        if(form.getId() != null) {
            Optional<ModelType> o = modelType_repository.findById(form.getId());
            if (o.isPresent()) {
                modelType = o.get();
            }
        }
        // Update fields.
        modelType.setName(form.getName());
        modelType_repository.save(modelType);
        return "redirect:/ItemType/";
    }

    @RequestMapping(value = "/SubjectArea/{cid}/Delete", method = RequestMethod.GET)
    public String DeleteSubjectArea(@PathVariable(name = "cid", value = "cid") Long id, Model model) {
        Optional<SubjectArea> o = subjectArea_repository.findById(id);
        if (o.isPresent()) {
            customerSubjectInterest_repository.deleteCustomerSubjectAreaBySubjectAreaID(id);
            subjectArea_repository.delete(o.get());
        }
        return "redirect:/ItemType";
    }

    @RequestMapping(value = "/SubjectArea/{cid}/Edit", method = RequestMethod.GET)
    public String EditSubjectArea(@PathVariable(name = "cid", value = "cid") Long ic, Model model) {
        Optional<SubjectArea> o = subjectArea_repository.findById(ic);
        if (o.isPresent()) {
            SubjectAreaForm subjectAreaForm = new SubjectAreaForm(o.get());
            model.addAttribute("subjectAreaForm", subjectAreaForm);
            return CreateSubjectArea(model);
        }
        return "redirect:/ItemType";
    }

    @GetMapping(path="/SubjectArea/Create")
    public String CreateSubjectArea(Model model) {
        model.addAttribute("title", "Edit SubjectArea");
        model.addAttribute("Area", "ItemTypes");
        model.addAttribute("Sub_Page", "SubjectAreaForm");

        if (!model.containsAttribute("subjectAreaForm")) {
            SubjectAreaForm subjectAreaForm = new SubjectAreaForm();

            model.addAttribute("title", "Create SubjectArea");
            model.addAttribute("subjectAreaForm", subjectAreaForm);
        }
        return "Master";
    }

    @PostMapping("/SubjectArea/Create")
    public String CreateSubjectArea(Model model, @Valid SubjectAreaForm form, BindingResult bindingResult) {
        model.addAttribute("title", "Create SubjectArea");
        model.addAttribute("Area", "ItemTypes");
        model.addAttribute("Sub_Page", "SubjectAreaForm");

        if (bindingResult.hasErrors()) return CreateSubjectArea(model);

        // Save in database.
        SubjectArea modelType = new SubjectArea();

        // Are we saving an existing ItemTypes?
        if(form.getId() != null) {
            Optional<SubjectArea> o = subjectArea_repository.findById(form.getId());
            if (o.isPresent()) {
                modelType = o.get();
            }
        }
        // Update fields.
        modelType.setName(form.getName());
        subjectArea_repository.save(modelType);
        return "redirect:/ItemType/";
    }
}
