package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Item {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name, description, availability;
    private double retail_price;
    private int stock;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getRetailPrice() { return retail_price; }
    public void setRetailPrice(double retail_price) { this.retail_price = retail_price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public ModelType getItemModelType() { return itemModelType; }
    public void setItemModelType(ModelType modelType) { this.itemModelType = modelType;}

    public SubjectArea getItemSubjectArea() { return itemSubjectArea; }
    public void setItemSubjectArea(SubjectArea subjectArea) {this.itemSubjectArea = subjectArea; }

    public List<SupplierItem> getSuppliers() { return suppliers; }
    public List<Review> getItemReviews() { return itemReviews; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL) @JsonIgnore
    private List<SaleLineItem> sale_line_items;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "suppliedItem", cascade = CascadeType.ALL)
	private List<SupplierItem> suppliers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewForItem", cascade = CascadeType.ALL)
    private List<Review> itemReviews;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_model_type_Id")
    private ModelType itemModelType;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_subject_area_Id")
    private SubjectArea itemSubjectArea;
}
