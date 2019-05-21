package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private List<CustomerModelInterestDTO> modelNames = new ArrayList<>();
    private List<CustomerSubjectInterestDTO> subjectAreaNames = new ArrayList<>();

    public CustomerForm() {}

    public void FromEntity(Customer customer) {
		this.setCustomerID(customer.getId());
		this.setName(customer.getPerson().getName());
		this.setAddress(customer.getAddress());
		this.setBalance(customer.getBalance());
		this.setCreditline(customer.getCreditline());
		this.setEmail(customer.getPerson().getEmail());
		this.setPhone(customer.getPerson().getPhone());
	}


    public List<CustomerModelInterestDTO> getModelNames() {
        return modelNames;
    }
    public void setModelNames(List<CustomerModelInterestDTO> modelNames) {
        this.modelNames = modelNames;
    }

    public List<CustomerSubjectInterestDTO> getSubjectAreaNames() {
        return subjectAreaNames;
    }
    public void setSubjectAreaNames(List<CustomerSubjectInterestDTO> subjectAreaNames) {
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