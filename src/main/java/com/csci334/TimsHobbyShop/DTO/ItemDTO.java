package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Item;

public class ItemDTO {
    public ItemDTO(Item i) {
        this.id = i.getId();
        this.name = i.getName();
        this.description = i.getDescription();
        this.availability = i.getAvailability();
        this.retail_price = i.getRetailPrice();
        this.stock = i.getStock();
    }
    public Long id;
    public String name, description, availability;
    public double retail_price;
    public int stock;

    public ItemDTO() {

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public double getRetail_price() {
        return retail_price;
    }
    public void setRetail_price(double retail_price) {
        this.retail_price = retail_price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}