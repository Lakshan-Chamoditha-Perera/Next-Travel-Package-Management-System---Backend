package com.nexttravel.hotel_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Options {
    double price;
    private String description;
    private int capacity;
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @ToString.Exclude
    private Hotel hotel;

}
