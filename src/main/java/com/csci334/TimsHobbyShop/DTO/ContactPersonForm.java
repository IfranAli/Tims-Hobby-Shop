package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.ContactPerson;
import com.csci334.TimsHobbyShop.model.Person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactPersonForm {
    @NotNull
    private Long supplerId;
    @NotNull @Size(min=2, max=30)
    private String name;
    @NotNull @Size(min=2, max=30)
    private String role = "CONTACT";

    private Long id;
    private Long personId;
    private String email, phone;
    private String address;

    public ContactPersonForm() {}
    public ContactPersonForm(ContactPerson c) {
        Person p = c.getPerson();
        personId = p.getId();
        id = c.getId();
        supplerId = c.getSupplier().getId();
        name = p.getName();
        email = p.getEmail();
        phone = p.getPhone();
        address = p.getAddress();
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplerId() {
        return supplerId;
    }
    public void setSupplerId(Long supplerId) {
        this.supplerId = supplerId;
    }

    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}