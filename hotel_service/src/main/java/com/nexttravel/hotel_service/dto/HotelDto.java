package com.nexttravel.hotel_service.dto;

import com.nexttravel.hotel_service.entity.Discount;
import com.nexttravel.hotel_service.entity.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    Map<String, Double> room_type_price = new HashMap<>();
    List<byte[]> imageList = new ArrayList<>();
    private String id;
    private String name;
    private int star_rate;
    private String geo_location;
    private String location;
    private String email;
    private List<String> contact_list = new ArrayList<>();
    private String is_pet_allowed;
    private String cancellation_criteria;
    private double tax;
    private List<DiscountDto>discount_dto_list =new ArrayList<>();
    private List<RoomDto>room_dto_list =new ArrayList<>();
}
