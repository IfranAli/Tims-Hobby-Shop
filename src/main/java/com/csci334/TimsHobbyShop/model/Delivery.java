package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Delivery {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String status;
    private double total;
    private Date deliveryDate = new java.sql.Date(new java.util.Date().getTime());

    public Delivery() {}
    public Delivery(Supplier supplier,  ContactPerson contactPerson, Store store) {
        setSupplier(supplier);
        setContactPerson(contactPerson);
        setStore(store);
    }

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_supplier_id")
    private Supplier supplier;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_contactPerson_id")
    private ContactPerson contactPerson;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_store_id")
    private Store store;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.REMOVE)
    private List<DeliveryItem> deliveryItems;

    public void updateTotal() {
        double total = 0;
        for (DeliveryItem deliveryItem : deliveryItems) {
            total += (deliveryItem.getPrice() * deliveryItem.getQuantity());
        }
        this.total = (total);
    }

    public List<DeliveryItem> getDeliveryItems() {
        return deliveryItems;
    }
    public void setDeliveryItems(List<DeliveryItem> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }
}
