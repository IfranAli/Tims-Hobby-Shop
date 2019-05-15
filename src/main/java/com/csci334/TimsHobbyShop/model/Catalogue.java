package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Catalogue {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Date date_publish;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDate_publish() {return date_publish;}
    public void setDate_publish(Date date_publish) {this.date_publish = date_publish;}

    public Supplier getSupplier() { return catalogueSupplier; }

	public List<SupplierItem> getSupplierItems() { return supplierItems; }

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_supplier_id")
    private Supplier catalogueSupplier;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemCatalogue", cascade = CascadeType.ALL)
	private List<SupplierItem> supplierItems;
}
