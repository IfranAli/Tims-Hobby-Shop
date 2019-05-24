package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Item;

public class ItemDTO {
    private Long id;
    private String name, description, availability;
    private double retail_price;
    private int stock;
    private long storeId;
    private String storeName;

    public ItemDTO() {}

    public ItemDTO(Item i) {
        this.id = i.getId();
        this.name = i.getName();
        this.description = i.getDescription();
        this.availability = i.getAvailability();
        this.retail_price = i.getRetailPrice();
        this.stock = i.getStock();
        this.storeId = i.getStore().getId();
        this.storeName = i.getStore().getName();
    }

    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public long getStoreId() {
        return storeId;
    }
    public void setStoreId(long storeId) {
        this.storeId = storeId;
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