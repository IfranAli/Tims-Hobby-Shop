package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.SubjectArea;

import javax.validation.constraints.NotNull;

public class SubjectAreaForm {
    @NotNull
    private String name;
    private Long id;

    public SubjectAreaForm() {}
    public SubjectAreaForm(SubjectArea m) {
        this.name = m.getName();
        this.id = m.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}