package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Supplier{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String companyName, address, creditline;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String name) { this.companyName= companyName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCreditline() { return creditline; }
    public void setCreditline(String creditline) { this.creditline = creditline; }

    public List<Catalogue> getCatalogues() { return catalogues; }

    @OneToMany(mappedBy = "catalogueSupplier", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Catalogue> catalogues;
}
