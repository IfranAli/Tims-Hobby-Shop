package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "fk_PersonId")
	private Person person;

	@OneToOne(fetch = FetchType.EAGER, optional = true, mappedBy = "member")
	@JoinColumn(name = "clubMembership")
	private ClubMember clubMembership;
	
    private String address, password, creditline;
	private double balanace;

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password;}

    public String getCreditline() { return creditline; }
    public void setCreditline(String creditline) { this.creditline = creditline; }

    public double getBalance() { return balanace; }
    public void setBalance(double balance) { this.balanace = balance; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

	public Person getPerson() { return person; }
	public void setPerson(Person person) { this.person = person; }

    public List<Sale> getSales() { return sales; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL) @JsonIgnore
    private List<Sale> sales;
    //@JsonManagedReference
}
