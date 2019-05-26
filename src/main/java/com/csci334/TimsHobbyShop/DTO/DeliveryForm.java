package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Delivery;
import com.csci334.TimsHobbyShop.model.DeliveryItem;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;

public class DeliveryForm {
    @NotNull
    private Long supplierId;
    @NotNull
    private Long contactPersonId;
    @NotNull
    private Long storeId;
    @NotNull @Size(min=2, max=30)
    private String status = "Initiated";
    @NotNull
    private Date deliveryDate = new java.sql.Date(new java.util.Date().getTime());

    private ArrayList<DeliveryItemDTO> deliveryItems = new ArrayList<>();

    private Long id;
    private double total;

    public DeliveryForm() {}
    public DeliveryForm(Delivery d) {
        setContactPersonId(d.getContactPerson().getId());
        setId(d.getId());
        setStatus(d.getStatus());
        setStoreId(d.getStore().getId());
        setSupplierId(d.getSupplier().getId());
        setTotal(d.getTotal());
        setDeliveryDate(d.getDeliveryDate());
        d.getDeliveryItems().forEach(i -> deliveryItems.add(new DeliveryItemDTO(i)));
    }

    public ArrayList<DeliveryItemDTO> getDeliveryItems() {
        return deliveryItems;
    }
    public void setDeliveryItems(ArrayList<DeliveryItemDTO> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getContactPersonId() {
        return contactPersonId;
    }
    public void setContactPersonId(Long contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public Long getStoreId() {
        return storeId;
    }
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}