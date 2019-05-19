package com.csci334.TimsHobbyShop.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
    import java.sql.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Date sale_date;
    private double total;
    private String status;
    private double discount;

    public void setDiscount(double discount) { this.discount = discount;}
    public double getDiscount() {return discount;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Date getSaleDate() {return sale_date;}
    public void setSaleDate(Date sale_date) {this.sale_date = sale_date;}

    public double getTotal() {return total;}
    public void setTotal(double total) { this.total = total;}

    public void updateTotal() {
        double total = 0;
        for (SaleLineItem saleLineItem : sale_line_items) {
            total += saleLineItem.getSale_price();
        }
        this.total = (total - getDiscount());
    }

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Customer getCustomer() {return customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    public List<SaleLineItem> getSale_line_items() {return sale_line_items;}
    public void setSale_line_items(List<SaleLineItem> sale_line_items) {
        this.sale_line_items = sale_line_items;
    }

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_customerId") @JsonBackReference
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleLineItem> sale_line_items;

}
