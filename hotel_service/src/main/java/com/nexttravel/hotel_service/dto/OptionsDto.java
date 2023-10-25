package com.nexttravel.hotel_service.dto;

import com.nexttravel.hotel_service.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsDto {
    double price;
    private String description;
    private int capacity;
    private int id;
    @ToString.Exclude
    private Hotel hotel;
}
