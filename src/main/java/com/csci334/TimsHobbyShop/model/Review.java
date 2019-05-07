package com.csci334.TimsHobbyShop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Review {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; // Lazy
    String description;
    int rating;

    public String getDescription() {return description;}
    public void setDescription(String description) { this.description = description;}

    public int getRating() {return rating;}
    public void setRating(int rating) {this.rating = rating;}

    public Item getReviewForItem() {return reviewForItem;}
    public void setReviewForItem(Item reviewForItem) {this.reviewForItem = reviewForItem;}

    public Person getReviewByPerson() {return reviewByPerson;}
    public void setReviewByPerson(Person reviewByPerson) {this.reviewByPerson = reviewByPerson;}

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_itemId") @JsonIgnore
    private Item reviewForItem;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fk_personId")
    @JsonBackReference
    private Person reviewByPerson;
}
