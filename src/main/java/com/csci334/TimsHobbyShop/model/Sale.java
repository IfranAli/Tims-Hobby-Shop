package com.csci334.TimsHobbyShop.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private Date order_date;
    private double total;
    private String status;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public Date getOrderDate() {return order_date;}
    public void setrderDate(Date order_date) {this.order_date = order_date;}

    public double getTotal() {return total;}
    public void setTotal(double total) {this.total = total;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Customer getCustomer() {return customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    public List<SaleLineItem> getSale_line_items() {return sale_line_items;}

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_customerId")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleLineItem> sale_line_items;

}
