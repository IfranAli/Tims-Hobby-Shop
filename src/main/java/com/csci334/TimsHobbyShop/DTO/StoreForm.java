package com.csci334.TimsHobbyShop.DTO;

import com.csci334.TimsHobbyShop.model.Item;
import com.csci334.TimsHobbyShop.model.Store;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class StoreForm {
	// TODO: Getters and setters
    private Long id;
    @NotNull @Size(min=2, max=30)
    private String name;
    private String address;

    public StoreForm() {}

	// Constructor to populate Item DTO from Store object. 
    public StoreForm(Store s) {
        id = s.getId();
        name = s.getName();
        address = s.getAddress();
	}

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
}
