package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Store{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name, address;

	public Store() {}

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

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<Item> getStore_items() {
        return store_items;
    }
    public void setStore_items(List<Item> store_items) {
        this.store_items = store_items;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }
    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL) @JsonIgnore
    private List<Item> store_items;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL) @JsonIgnore
    private List<Delivery> deliveries;
}
