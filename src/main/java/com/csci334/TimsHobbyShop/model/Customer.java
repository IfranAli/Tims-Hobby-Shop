package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "fk_PersonId")
	private Person person;

	@OneToOne(fetch = FetchType.EAGER, optional = true, mappedBy = "member")
	@JoinColumn(name = "clubMembership")
	private ClubMember clubMembership;

    private String address, creditline;
	private double balance;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCreditline() { return creditline; }
    public void setCreditline(String creditline) { this.creditline = creditline; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

	public Person getPerson() { return person; }
	public void setPerson(Person person) { this.person = person; }

    public ClubMember getClubMembership() { return clubMembership; }
    public void setClubMembership(ClubMember clubMembership) { this.clubMembership = clubMembership; }

    public List<Sale> getSales() { return sales; }

    public List<CustomerSubjectInterest> getSubjectAreaInterests() {
        return subjectAreaInterests;
    }
    public List<CustomerModelInterest> getModelTypeInterests() {
        return modelTypeInterests;
    }

    public void setSubjectAreaInterests(List<CustomerSubjectInterest> subjectAreaInterests) {
        this.subjectAreaInterests = subjectAreaInterests;
    }
    public void setModelTypeInterests(List<CustomerModelInterest> modelTypeInterests) {
        this.modelTypeInterests = modelTypeInterests;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL) @JsonIgnore
    private List<Sale> sales;
    //@JsonManagedReference

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerWithSubjectInterest", cascade = CascadeType.ALL)
    private List<CustomerSubjectInterest> subjectAreaInterests;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerWithModelInterest", cascade = CascadeType.ALL)
    private List<CustomerModelInterest> modelTypeInterests;
}
