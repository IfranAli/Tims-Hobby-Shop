package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Customer;
import com.csci334.TimsHobbyShop.model.Supplier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class SupplierForm {
    private Long id;

    @NotNull @Size(min=2, max=30)
    private String companyName;
    private String address, creditline;

    public SupplierForm() {};
    public SupplierForm(Supplier supplier) {
        this.id = supplier.getId();
        this.companyName = supplier.getCompanyName();
        this.address = supplier.getAddress();
        this.creditline = supplier.getCreditline();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditline() {
        return creditline;
    }
    public void setCreditline(String creditline) {
        this.creditline = creditline;
    }
}