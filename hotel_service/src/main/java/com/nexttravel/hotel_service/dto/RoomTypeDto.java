package com.nexttravel.hotel_service.dto;

import com.nexttravel.hotel_service.entity.Hotel;
import com.nexttravel.hotel_service.entity.Room;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDto {
    private String name;
    @ToString.Exclude
    private HotelDto hotel;
    private double price;
    @ToString.Exclude
    private List<RoomDto> room_list = new ArrayList<>();
}
