package com.csci334.TimsHobbyShop.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemForm {

    private Long id;
    private String description;
    @NotNull @Size(min=2, max=30)
    private String name, availability;
    @NotNull @Min(0)
    private int stock;
    @NotNull @Min(1)
    private double retail_price;
    @NotNull
    private long model_type;
    @NotNull
    private long model_subject_area;

    public ItemForm() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public long getModel_type() {
        return model_type;
    }
    public void setModel_type(long model_type) {
        this.model_type = model_type;
    }

    public long getModel_subject_area() {
        return model_subject_area;
    }
    public void setModel_subject_area(long model_subject_area) {
        this.model_subject_area = model_subject_area;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getRetail_price() {
        return retail_price;
    }
    public void setRetail_price(double retail_price) {
        this.retail_price = retail_price;
    }
}
