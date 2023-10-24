package com.nexttravel.hotel_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private boolean is_available;
    private String description;
    private int capacity;
    private String id;
    @ToString.Exclude
    private HotelDto hotel;
    private RoomTypeDto room_type;
}
