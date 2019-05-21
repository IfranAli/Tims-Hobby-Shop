package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.repository.CustomerModelInterest_Repository;

public class CustomerModelInterestDTO {
    private Long customerID;
    private Long modelTypeID;
    private String modelTypeName;
    private Boolean isActive = false;

    public CustomerModelInterestDTO(Long customerID, Long modelTypeID, String modelTypeName) {
        this.customerID = customerID;
        this.modelTypeID = modelTypeID;
        this.modelTypeName = modelTypeName;
        if (this.customerID != null) {
            isActive = true;
        }
    }
    public CustomerModelInterestDTO(CustomerModelInterest_Repository.CustomerModelInterestInterface m) {
        this.customerID = m.getCustomerID();
        this.modelTypeID = m.getModelTypeID();
        this.modelTypeName = m.getModelTypeName();
        if (this.customerID != null) {
            isActive = true;
        }
    }
    public CustomerModelInterestDTO() {};

    public Long getCustomerID() {
        return customerID;
    }
    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getModelTypeID() {
        return modelTypeID;
    }
    public void setModelTypeID(Long modelTypeID) {
        this.modelTypeID = modelTypeID;
    }

    public String getModelTypeName() {
        return modelTypeName;
    }
    public void setModelTypeName(String modelTypeName) {
        this.modelTypeName = modelTypeName;
    }

    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }
}