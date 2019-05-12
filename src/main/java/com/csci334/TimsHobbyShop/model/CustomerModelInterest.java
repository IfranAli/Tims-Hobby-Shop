package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class CustomerModelInterest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_customer_id") @JsonIgnore
    private Customer customerWithModelInterest;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_model_type_id") @JsonIgnore
    private ModelType modelType;

    public ModelType getModelType() { return modelType; }
    public void setModelType(ModelType modelType) { this.modelType = modelType; }

    public Customer getCustomerWithModelInterest() {
        return customerWithModelInterest;
    }
    public void setCustomerWithModelInterest(Customer customerWithModelInterest) {
        this.customerWithModelInterest = customerWithModelInterest;
    }
}
