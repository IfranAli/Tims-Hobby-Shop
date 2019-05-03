package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class SupplierItem{
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	private double supplier_price;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }


    public Supplier getSupplier() { return itemSupplier; }

    //@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_SupplierId")
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_SupplierId")
	private Supplier itemSupplier;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_Item")
	private Item suppliedItem;
}
