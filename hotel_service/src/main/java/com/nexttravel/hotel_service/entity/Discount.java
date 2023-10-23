package com.nexttravel.hotel_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.ToString;

@Entity
public class Discount {
    @Id
    private String code;
    private boolean is_available;
    private double discount_percentage;
    private String description;

    @ManyToOne
    @ToString.Exclude
    private Hotel hotel;
}
