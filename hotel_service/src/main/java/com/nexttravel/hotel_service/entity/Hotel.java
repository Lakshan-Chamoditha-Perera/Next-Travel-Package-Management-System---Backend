package com.nexttravel.hotel_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
public class Hotel {
    @ElementCollection
    @CollectionTable(name = "hotel_option_price", joinColumns = @JoinColumn(name = "hotel_id"))
    Map<String, Double> room_type_price = new HashMap<>();
    @Id
    private String id;
    private String name;
    private int star_rate;
    @Lob
    private String geo_location;
    private String location;
    private String email;
    @ElementCollection
    @CollectionTable(name = "contact", joinColumns = @JoinColumn(name = "hotel_id"))
    private List<String> contact_list = new ArrayList<>();
    private String is_pet_allowed;
    private String cancellation_criteria;


}
