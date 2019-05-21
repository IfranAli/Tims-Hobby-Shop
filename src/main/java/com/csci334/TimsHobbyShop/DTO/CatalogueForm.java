package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Catalogue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;

public class CatalogueForm {
    private Long id;
    private Date date_publish;

    private ArrayList<SupplierItemDTO> supplierItems = new ArrayList<>();

    public CatalogueForm() {};
    public CatalogueForm(Catalogue catalogue) {
        this.id = catalogue.getId();
        this.date_publish = catalogue.getDate_publish();
        catalogue.getSupplierItems().forEach(i -> supplierItems.add(new SupplierItemDTO(i)));
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_publish() {
        return date_publish;
    }
    public void setDate_publish(Date date_publish) {
        this.date_publish = date_publish;
    }

    public ArrayList<SupplierItemDTO> getSupplierItems() {
        return supplierItems;
    }
    public void setSupplierItems(ArrayList<SupplierItemDTO> supplierItems) {
        this.supplierItems = supplierItems;
    }
}