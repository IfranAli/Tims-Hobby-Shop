package com.csci334.TimsHobbyShop.DTO;

public class ItemDTO {
    public ItemDTO(Long id, String name, String description, String availability, double retail_price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.availability = availability;
        this.retail_price = retail_price;
        this.stock = stock;
    }
    public Long id;
    public String name, description, availability;
    public double retail_price;
    public int stock;
}