package com.nexttravel.hotel_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Room {
    private boolean is_available;
    private String room_type;
    private String description;
    private int capacity;
    @Id
    private String id;
    @ManyToOne
    @ToString.Exclude
    private Hotel hotel;
}
