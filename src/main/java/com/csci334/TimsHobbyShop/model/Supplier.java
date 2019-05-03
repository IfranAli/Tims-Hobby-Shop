package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Supplier{
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String companyName, address, creditline;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String name) { this.companyName= companyName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCreditline() { return creditline; }
    public void setCreditline(String creditline) { this.creditline = creditline; }

    public List<SupplierItem> getSupplierItems() { return supplierItems; }

    @OneToMany(mappedBy = "itemSupplier", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SupplierItem> supplierItems;
}
