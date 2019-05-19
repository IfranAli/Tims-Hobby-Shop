package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.model.SaleLineItem;

public class SaleLineItemDTO{
    // SaleLineItem Fields
    private Long saleId;
    private int quantity;
    private double sale_price;

    // Item Fields
    private Long itemId;
    private String name, description, availability;
    private double retail_price;
    private int stock;

    public SaleLineItemDTO() {}
    public SaleLineItemDTO(SaleLineItem s) {
        Item i = s.getItem();
        // SaleLineItem Fields
        this.saleId = s.getId();
        this.quantity = s.getQuantity();
        this.sale_price = s.getSale_price();

        // Item Fields
        this.itemId = i.getId();
        this.name = i.getName();
        this.description = i.getDescription();
        this.availability = i.getAvailability();
        this.retail_price = i.getRetailPrice();
        this.stock = i.getStock();
    }

    public Long getSaleId() {
        return saleId;
    }
    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSale_price() {
        return sale_price;
    }
    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
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