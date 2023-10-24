package com.nexttravel.hotel_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
    @Id
    private String name;

    @ManyToOne()
    @ToString.Exclude
    private Hotel hotel;

    private double price;

    @OneToMany(mappedBy = "room_type", targetEntity = Room.class)
    @ToString.Exclude
    private List<Room> room_list = new ArrayList<>();

}
