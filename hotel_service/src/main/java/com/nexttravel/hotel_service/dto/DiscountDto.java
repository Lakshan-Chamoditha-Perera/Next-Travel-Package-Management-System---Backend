package com.nexttravel.hotel_service.dto;

import com.nexttravel.hotel_service.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {
    private String code;
    private boolean is_available;
    private double discount_percentage;
    private String description;
    @ToString.Exclude
    private HotelDto hotel;
}
