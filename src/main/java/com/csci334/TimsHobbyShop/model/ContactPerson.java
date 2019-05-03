package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity

public class ContactPerson {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_PersonId", unique = true)
	Person person;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_SupplierId")
	Supplier supplier;

}
