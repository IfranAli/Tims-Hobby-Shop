package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Date;
import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class ClubMember{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_CustomerId")
	Customer member;

    private Date joinDate;

	public Date getJoinDate() { return joinDate; }
	public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
}