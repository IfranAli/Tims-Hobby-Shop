package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.repository.CustomerSubjectInterest_Repository;

public class CustomerSubjectInterestDTO {
    private Long customerID;
    private Long subjectAreaID;
    private String subjectAreaName;
    private Boolean isActive = false;

    public CustomerSubjectInterestDTO(Long customerID, Long subjectAreaID, String subjectAreaName) {
        this.customerID = customerID;
        this.subjectAreaID = subjectAreaID;
        this.subjectAreaName = subjectAreaName;
        if (this.customerID != null) {
            isActive = true;
        }
    }
    public CustomerSubjectInterestDTO(CustomerSubjectInterest_Repository.CustomerSubjectInterestInterface i) {
        this.customerID = i.getCustomerID();
        this.subjectAreaID = i.getSubjectAreaID();
        this.subjectAreaName = i.getSubjectAreaName();
        if (this.customerID != null) {
            isActive = true;
        }
    }
    public CustomerSubjectInterestDTO() {}

    public Long getCustomerID() {
        return customerID;
    }
    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getSubjectAreaID() {
        return subjectAreaID;
    }
    public void setSubjectAreaID(Long subjectAreaID) {
        this.subjectAreaID = subjectAreaID;
    }

    public String getSubjectAreaName() {
        return subjectAreaName;
    }
    public void setSubjectAreaName(String subjectAreaName) {
        this.subjectAreaName = subjectAreaName;
    }

    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }
}