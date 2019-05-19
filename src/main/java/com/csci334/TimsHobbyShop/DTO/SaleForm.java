package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.model.Sale;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;

public class SaleForm {
    @NotNull
    private Date sale_date;
    @NotNull
    private String status;
    @NotNull @Min(0)
    private double discount;

    private Long id;
    private double total;

    private ArrayList<SaleLineItemDTO> saleLineItemDTOs;

    public SaleForm() {
        // Set current date
        sale_date = new java.sql.Date(new java.util.Date().getTime());
        saleLineItemDTOs = new ArrayList<SaleLineItemDTO>();
    }

	// Constructor to populate Item DTO from Sale object.
    public SaleForm(Sale i) {
        saleLineItemDTOs = new ArrayList<SaleLineItemDTO>();
        setId(i.getId());
        setTotal(i.getTotal());
        setDiscount(i.getDiscount());
        setStatus(i.getStatus());
        setSale_date(i.getSaleDate());
	}

    public ArrayList<SaleLineItemDTO> getSaleLineItemDTOs() {
        return saleLineItemDTOs;
    }
    public void setSaleLineItemDTOs(ArrayList<SaleLineItemDTO> saleLineItemDTOs) {
        this.saleLineItemDTOs = saleLineItemDTOs;
    }

    public Date getSale_date() {
        return sale_date;
    }
    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
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
