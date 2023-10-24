package com.nexttravel.hotel_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Hotel {

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "hotel_id"))
            @Column(columnDefinition = "LONGBLOB")
    List<byte[]> image_list = new ArrayList<>();


    @Id
    private String id;
    private String name;
    private int star_rate;

    @Column(columnDefinition = "TEXT")
    private String geo_location;
    private String location;
    private String email;
    private String contact;
    private String is_pet_allowed;
    private String cancellation_criteria;
    private double tax;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Discount> discount_list = new ArrayList<>();

    @OneToMany(cascade ={ CascadeType.REMOVE,CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Options> options_list = new ArrayList<>();

}
