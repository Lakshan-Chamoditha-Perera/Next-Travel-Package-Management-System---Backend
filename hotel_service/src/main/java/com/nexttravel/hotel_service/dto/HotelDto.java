package com.nexttravel.hotel_service.dto;

import com.nexttravel.hotel_service.entity.Discount;
import com.nexttravel.hotel_service.entity.Options;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    List<byte[]> image_list = new ArrayList<>();
    private String id;
    private String name;
    private int star_rate;
    private String geo_location;
    private String location;
    private String email;
    private String contact;
    private String is_pet_allowed;
    private String cancellation_criteria;
    private double tax;
    private List<DiscountDto> discount_list = new ArrayList<>();
    private List<OptionsDto> options_list = new ArrayList<>();
}
