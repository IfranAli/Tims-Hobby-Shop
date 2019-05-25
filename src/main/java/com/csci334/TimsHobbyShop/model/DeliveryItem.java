package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class DeliveryItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_supplierItem")
    private SupplierItem supplierItem;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_delivery") @JsonIgnore
    private Delivery delivery;

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

    public SupplierItem getSupplierItem() {
        return supplierItem;
    }
    public void setSupplierItem(SupplierItem supplierItem) {
        this.supplierItem = supplierItem;
    }

    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
