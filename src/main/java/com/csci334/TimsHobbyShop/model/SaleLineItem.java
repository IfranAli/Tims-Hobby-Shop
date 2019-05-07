package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class SaleLineItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; // Lazy
    private int quantity;
    private double sale_price;

    public double getSale_price() { return sale_price; }
    public void setSale_price(double sale_price) { this.sale_price = sale_price; }

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public Item getItem() {return item;}
    public void setItem(Item item) {this.item = item;}

    public Sale getSale() {return sale;}
    public void setSale(Sale sale) {this.sale = sale;}

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_itemId")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_saleId") @JsonIgnore
    private Sale sale;
}
