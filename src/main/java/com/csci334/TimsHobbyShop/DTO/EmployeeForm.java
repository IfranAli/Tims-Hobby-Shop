package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Person;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class EmployeeForm {
    @NotNull @Size(min=2, max=30)
    private String username;
    @NotNull @Size(min=2, max=30)
    private String name;
    private String email;
    private String phone;
//    private String address;
    private Long employeeID;
    @NotNull
    private String role;

    @NotNull @Size(min=2, max=30)
    private String password;
    @NotNull @Size(min=2, max=30)
    private String passwordConfirm;

    public EmployeeForm() {}

    public void FromEntity(Person p) {
		this.setEmployeeID(p.getId());
		this.setName(p.getName());
		this.setEmail(p.getEmail());
        this.setPhone(p.getPhone());
        this.setPassword(p.getPassword());
        this.setPasswordConfirm(p.getPassword());
        this.setRole(p.getRole());
        this.setUsername(p.getUsername());
//		this.setAddress(p.getAddress());
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

    public Long getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}