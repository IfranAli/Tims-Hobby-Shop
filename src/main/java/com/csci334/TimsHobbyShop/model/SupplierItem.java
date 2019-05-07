package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class SupplierItem{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	private double supplier_price;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public Catalogue getCatalogue() { return itemCatalogue; }

    public double getPrice() { return supplier_price; }
    public void setPrice(double price) { supplier_price = price; }

    //@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_SupplierId")
    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_CatalogueId")
	private Catalogue itemCatalogue;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_Item")
	private Item suppliedItem;
}
