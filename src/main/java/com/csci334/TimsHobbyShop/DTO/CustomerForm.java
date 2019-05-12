package com.csci334.TimsHobbyShop.DTO;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerForm {
//    @NotNull @Size(min=2, max=30)
//    private String username;
//    @NotNull @Size(min=2, max=30)
//    private String password;
//    @NotNull @Size(min=2, max=30)
//    private String passwordConfirm;
//    @NotNull @Size(min=2, max=30)

    @NotNull @Size(min=2, max=30)
    private String name;
    private String email;
    private String phone;
    private String address;
    private String creditline;
    private Long customerID;
    @NotNull @Min(0)
    private double balance = 0;

    private HashMap<String, Boolean> modelNames;
    private HashMap<String, Boolean> subjectAreaNames;

    public CustomerForm() {}

    public HashMap<String, Boolean> getModelNames() {
        return modelNames;
    }
    public void setModelNames(HashMap<String, Boolean> modelNames) {
        this.modelNames = modelNames;
    }

    public HashMap<String, Boolean> getSubjectAreaNames() {
        return subjectAreaNames;
    }
    public void setSubjectAreaNames(HashMap<String, Boolean> subjectAreaNames) {
        this.subjectAreaNames = subjectAreaNames;
    }

    public Long getCustomerID() {
        return customerID;
    }
    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
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

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditline() {
        return creditline;
    }
    public void setCreditline(String creditline) {
        this.creditline = creditline;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}