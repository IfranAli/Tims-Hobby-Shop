package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.DeliveryItem;
import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.model.SupplierItem;

public class DeliveryItemDTO {
    private Long id;
    private int quantity;
    private double price;

    private Long supplierItemId;
    private Long itemId;
    private String name, description;
    private double supplier_price;

    public DeliveryItemDTO() {}
    public DeliveryItemDTO(DeliveryItem d) {
        this.id = d.getId();
        this.quantity = d.getQuantity();
        this.price = d.getPrice();

        SupplierItem s = d.getSupplierItem();

        this.supplierItemId = s.getId();
        this.supplier_price = s.getPrice();

        Item i = s.getSuppliedItem();
        this.name = i.getName();
        this.itemId = i.getId();
        this.description = i.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getSupplierItemId() {
        return supplierItemId;
    }

    public void setSupplierItemId(Long supplierItemId) {
        this.supplierItemId = supplierItemId;
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

    public double getSupplier_price() {
        return supplier_price;
    }

    public void setSupplier_price(double supplier_price) {
        this.supplier_price = supplier_price;
    }
}