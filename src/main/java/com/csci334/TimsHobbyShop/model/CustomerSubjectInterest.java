package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class CustomerSubjectInterest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_customer_id") @JsonIgnore
    private Customer customerWithSubjectInterest;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_subject_area_id") @JsonIgnore
    private SubjectArea subjectArea;

    public SubjectArea getSubjectArea() { return subjectArea; }
    public void setSubjectArea(SubjectArea subjectArea) { this.subjectArea = subjectArea; }

    public Customer getCustomerWithSubjectInterest() {
        return customerWithSubjectInterest;
    }
    public void setCustomerWithSubjectInterest(Customer customerWithSubjectInterest) {
        this.customerWithSubjectInterest = customerWithSubjectInterest;
    }
}
